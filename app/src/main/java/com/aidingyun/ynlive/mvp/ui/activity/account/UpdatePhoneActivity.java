package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.di.component.DaggerUpdatePhoneComponent;
import com.aidingyun.ynlive.di.module.UpdatePhoneModule;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.contract.UpdatePhoneContract;
import com.aidingyun.ynlive.mvp.presenter.UpdatePhonePresenter;
import com.aidingyun.ynlive.mvp.ui.widget.CountDownTextView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.lzy.okgo.OkGo;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import okhttp3.OkHttpClient;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class UpdatePhoneActivity extends BaseActivity<UpdatePhonePresenter> implements UpdatePhoneContract.View,View.OnClickListener {


    /**
     * 手机号输入框
     */
    private EditText phone_number;
    /**
     * 删除按钮
     */
    private ImageView delete;
    /**
     * 区号
     */
    private TextView phone_area_code;
    /**
     * 发送验证码按钮
     */
    private CountDownTextView verify_code_tips;
    /**
     * 输入验证码
     */
    private EditText input_verify_code;
    /**
     * 保存
     */
    private TextView tv_save_btn;

    private UpdateVersionUtils updateVersionUtils = null;

    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    public static void start(Context context) {
        Router.newIntent(context)
                .to(UpdatePhoneActivity.class)
                .launch();
    }
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUpdatePhoneComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .updatePhoneModule(new UpdatePhoneModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_update_phone; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInit(this, "修改手机号", true);

        updateVersionUtils = new UpdateVersionUtils();
        preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
        phone_number = findViewById(R.id.phone_number);
        input_verify_code = findViewById(R.id.input_verify_code);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);
        phone_area_code = findViewById(R.id.phone_area_code);
        phone_area_code.setOnClickListener(this);
        verify_code_tips = findViewById(R.id.verify_code_tips);
        verify_code_tips.setOnClickListener(this);
        tv_save_btn = findViewById(R.id.tv_save_btn);
        tv_save_btn.setOnClickListener(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消指定tag的请求
        OkGo.getInstance().cancelTag(this); //取消全部请求
        OkGo.getInstance().cancelAll(); //取消OkHttpClient的全部请求
        OkGo.cancelAll(new OkHttpClient());
        OkGo.cancelTag(new OkHttpClient(),"且指定tag");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.verify_code_tips:
                sendVerify_code(phone_number.getText().toString().trim());
                break;
            case R.id.tv_save_btn:
                changeBindPhone(phone_number.getText().toString().trim(),input_verify_code.getText().toString().trim());
                break;
            case R.id.tv_login:
                LoginActivity.start(this);
                finish();
                break;
            case R.id.delete:
                phone_number.setText("");
                break;
        }
    }

    private void changeBindPhone(String phone, String code) {
        if (TextUtils.isEmpty(phone)){
            ToastUtils.showError(UpdatePhoneActivity.this,"手机号不能为空!");
            return;
        }
        if (TextUtils.isEmpty(code)){
            ToastUtils.showError(UpdatePhoneActivity.this,"验证码不能为空!");
            return;
        }

        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("phone", phone);
        reqBody.put("verify_code", code);
        updateVersionUtils.postByName(Global.CHANGE_PHONE_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("SplashActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        ToastUtils.showError(UpdatePhoneActivity.this,jsonObject.getString("msg"));
                        finish();
                    }else{
                        ToastUtils.showError(UpdatePhoneActivity.this,jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("SplashActivity","updateVersionUtils++++++++++++++++"+error);
            }
        });

    }

    @SuppressLint("MissingPermission")
    public void sendVerify_code(String phone) {
        if (TextUtils.isEmpty(phone)){
            ToastUtils.showError(UpdatePhoneActivity.this,"手机号不能为空!");
            return;
        }

        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("phone", phone);
        updateVersionUtils.postByName(Global.SEND_VERIFY_CODE_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("SplashActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        ToastUtils.show(UpdatePhoneActivity.this,jsonObject.getString("msg"));
                        verify_code_tips.setCountDownMillis(60000);
                        verify_code_tips.start();
                    }else{
                        ToastUtils.showError(UpdatePhoneActivity.this,jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("SplashActivity","updateVersionUtils++++++++++++++++"+error);
            }
        });
    }
}
