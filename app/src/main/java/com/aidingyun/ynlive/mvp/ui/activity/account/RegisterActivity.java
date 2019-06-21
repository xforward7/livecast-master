package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.di.component.DaggerRegisterComponent;
import com.aidingyun.ynlive.di.module.RegisterModule;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.contract.RegisterContract;
import com.aidingyun.ynlive.mvp.model.entity.LoginInfo;
import com.aidingyun.ynlive.mvp.model.entity.Md5;
import com.aidingyun.ynlive.mvp.presenter.RegisterPresenter;
import com.aidingyun.ynlive.mvp.ui.activity.MainActivity;
import com.aidingyun.ynlive.mvp.ui.widget.CountDownTextView;
import com.google.gson.Gson;
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


public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View,View.OnClickListener {

    /**
     * 密码输入框
     */
    private EditText phone_pwd;
    /**
     * 确认密码输入框
     */
    private EditText phone_pwd_check;
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
     * 下一步
     */
    private Button btn_next;
    /**
     * 勾选确认条款
     */
    private CheckBox rem_clause_check;
    /**
     * 条款
     */
    private TextView tv_web;
    /**
     * 已有账号直接登录
     */
    private TextView tv_login;

    private UpdateVersionUtils updateVersionUtils = null;

    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    public static void start(Context context) {
        start(context, 0);
    }

    public static void start(Context context, int flag) {
        Router.newIntent(context)
                .to(RegisterActivity.class)
                .addFlag(flag)
                .launch();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRegisterComponent
                .builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_registerorforgetpwd;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInit(this, "注册", true);
        updateVersionUtils = new UpdateVersionUtils();
        preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
        phone_number = findViewById(R.id.phone_number);
        phone_pwd = findViewById(R.id.phone_pwd);
        phone_pwd_check = findViewById(R.id.phone_pwd_check);
        input_verify_code = findViewById(R.id.input_verify_code);
        rem_clause_check = findViewById(R.id.rem_clause_check);
        tv_web = findViewById(R.id.tv_web);
        tv_web.setOnClickListener(this);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);
        phone_area_code = findViewById(R.id.phone_area_code);
        phone_area_code.setOnClickListener(this);
        verify_code_tips = findViewById(R.id.verify_code_tips);
        verify_code_tips.setOnClickListener(this);
        tv_login = findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setText("下一步");
        btn_next.setOnClickListener(this);

        phone_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {

            }
            @Override
            public void afterTextChanged(Editable edt) {
                try {
                    String temp = edt.toString();
                    String tem = temp.substring(temp.length()-1, temp.length());
                    char[] temC = tem.toCharArray();
                    int mid = temC[0];
                    if(mid>=48&&mid<=57){//数字
                        return;
                    }
                    if(mid>=65&&mid<=90){//大写字母
                        return;
                    }
                    if(mid>97&&mid<=122){//小写字母
                        return;
                    }
                    edt.delete(temp.length()-1, temp.length());
                } catch (Exception e) {
// TODO: handle exception
                }

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
    public void killMyself() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.verify_code_tips:
                sendVerify_code(phone_number.getText().toString().trim());
                break;
            case R.id.btn_next:
                register(phone_number.getText().toString().trim(),phone_pwd.getText().toString().trim(),input_verify_code.getText().toString().trim(),"","","","");
                break;
            case R.id.tv_login:
                LoginActivity.start(this);
                finish();
                break;
            case R.id.tv_web:

                break;
            case R.id.delete:
                phone_number.setText("");
                break;
        }
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


    @SuppressLint("MissingPermission")
    public void sendVerify_code(String phone) {
        if (TextUtils.isEmpty(phone)){
            ToastUtils.showError(RegisterActivity.this,"手机号不能为空!");
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
                        ToastUtils.show(RegisterActivity.this,jsonObject.getString("msg"));
                        verify_code_tips.setCountDownMillis(60000);
                        verify_code_tips.start();
                    }else{
                        ToastUtils.showError(RegisterActivity.this,jsonObject.getString("msg"));
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
    public void register(String phone,String password,String verify_code,String qq_openid,String wx_openid,String auto_login,String user_source) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(verify_code)){
            ToastUtils.showError(RegisterActivity.this,"注册信息不能为空!");
            return;
        }
        if (!rem_clause_check.isChecked()){
            ToastUtils.showError(RegisterActivity.this,"请勾选直播条款!");
            return;
        }

        if (TextUtils.isEmpty(phone_pwd_check.getText().toString().trim())){
            ToastUtils.showError(RegisterActivity.this,"确认密码不能为空!");
            return;
        }
        if (!phone_pwd_check.getText().toString().trim().equals(phone_pwd.getText().toString().trim())){
            ToastUtils.showError(RegisterActivity.this,"确认密码不一致!");
            return;
        }

//        String siteid = ABaseService.siteid;
//        if (TextUtils.isEmpty(siteid)){
//            ToastUtils.showError(RegisterActivity.this,"siteid不能为空!");
//            return;
//        }

        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("phone", phone);
        reqBody.put("password", Md5.strToMd5Low32(password));
        reqBody.put("verify_code", verify_code);
//        reqBody.put("wx_openid", wx_openid);
//        reqBody.put("auto_login", auto_login+"");
//        reqBody.put("user_source", user_source);
        updateVersionUtils.postByName(Global.REGISTER_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("RegisterActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    Gson gson = new Gson();
                    ABaseService.loginInfo = gson.fromJson(result, LoginInfo.class);
                    String jsonInfo=gson.toJson(ABaseService.loginInfo); //将对象转换成Json
                    if (code.equals("success")){
                        ToastUtils.show(RegisterActivity.this,jsonObject.getString("msg"));
                        editor.putBoolean("islogin",true);
                        editor.putString("token",ABaseService.loginInfo.getToken());
                        editor.putString("userinfo",jsonInfo);
                        editor.commit();
                        ABaseService.islogin = true;
                        MainActivity.start(RegisterActivity.this);
                        finish();
                    }else{
                        ToastUtils.showError(RegisterActivity.this,jsonObject.getString("msg"));
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
