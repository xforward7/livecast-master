package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.di.component.DaggerUpdatePwdComponent;
import com.aidingyun.ynlive.di.module.UpdatePwdModule;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.contract.UpdatePwdContract;
import com.aidingyun.ynlive.mvp.model.entity.Md5;
import com.aidingyun.ynlive.mvp.presenter.UpdatePwdPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.lzy.okgo.OkGo;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class UpdatePwdActivity extends BaseActivity<UpdatePwdPresenter> implements UpdatePwdContract.View {

    /**
     * 当前密码
     * @param context
     */
    EditText ed_current_pwd;
    /**
     *新密码
     * @param context
     */
    EditText ed_new_pwd;
    /**
     *确认新密码
     * @param context
     */
    EditText ed_check_new_pwd;

    /**
     * 提交
     * @param context
     */
    TextView tv_submit_btn;
    /**
     * 忘记密码
     * @param context
     */
    TextView tv_foget_pass;

    private UpdateVersionUtils updateVersionUtils = null;

    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    public static void start(Context context) {

        Router.newIntent(context)
                .to(UpdatePwdActivity.class)
                .launch();
    }
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUpdatePwdComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .updatePwdModule(new UpdatePwdModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_update_pwd; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInit(this, "修改密码", true);
        updateVersionUtils = new UpdateVersionUtils();
        preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
        ed_current_pwd = findViewById(R.id.ed_current_pwd);
        ed_new_pwd = findViewById(R.id.ed_new_pwd);
        ed_check_new_pwd = findViewById(R.id.ed_check_new_pwd);
        tv_submit_btn = findViewById(R.id.tv_submit_btn);
        ed_new_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tv_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePass(ed_current_pwd.getText().toString().trim(),ed_new_pwd.getText().toString().trim(),ed_check_new_pwd.getText().toString().trim());
            }
        });
        tv_foget_pass = findViewById(R.id.tv_foget_pass);
        tv_foget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

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
    protected void onDestroy() {
        super.onDestroy();
        //取消指定tag的请求
        OkGo.getInstance().cancelTag(this); //取消全部请求
//        OkGo.getInstance().cancelAll(); //取消OkHttpClient的全部请求
//        OkGo.cancelAll(new OkHttpClient());
//        OkGo.cancelTag(new OkHttpClient(),"且指定tag");
    }

    @Override
    public void killMyself() {
        finish();
    }


    private void changePass(String current_pass, String new_pass, String comfirm_pass) {
        if (TextUtils.isEmpty(current_pass) && TextUtils.isEmpty(new_pass) && TextUtils.isEmpty(comfirm_pass)){
            ToastUtils.showError(UpdatePwdActivity.this,"修改信息填写不完整!");
            return;
        }

        if (!new_pass.equals(comfirm_pass)){
            ToastUtils.showError(UpdatePwdActivity.this,"两次密码不一致!");
            return;
        }


        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("password", Md5.strToMd5Low32(current_pass));
        reqBody.put("pwd1", Md5.strToMd5Low32(new_pass));
        reqBody.put("pwd2", Md5.strToMd5Low32(comfirm_pass));
        updateVersionUtils.postByName(Global.CHANGE_PASS_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("UpdatePwdActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        ToastUtils.show(UpdatePwdActivity.this,jsonObject.getString("msg"));
                        finish();
                    }else{
                        ToastUtils.showError(UpdatePwdActivity.this,jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("UpdatePwdActivity","updateVersionUtils++++++++++++++++"+error);
            }
        });

    }
}
