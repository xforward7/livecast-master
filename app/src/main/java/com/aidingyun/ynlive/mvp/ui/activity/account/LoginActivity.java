package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.core.utils.KeyboardUtil;
import com.aidingyun.core.widget.DialogLoading;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.di.component.DaggerLoginComponent;
import com.aidingyun.ynlive.di.module.LoginModule;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.contract.LoginContract;
import com.aidingyun.ynlive.mvp.model.entity.LoginInfo;
import com.aidingyun.ynlive.mvp.model.entity.Md5;
import com.aidingyun.ynlive.mvp.presenter.LoginPresenter;
import com.facebook.stetho.common.LogUtil;
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


/**
 * @author 翁嘉若
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View,View.OnClickListener {


//    @BindView(R.id.tv_register)
//    private TextView tv_register;
    /**
     * 注册按钮
     */
    private TextView tv_register;
    /**
     * 忘记密码
     */
    private TextView tv_forget;
    /**
     * 登录
     */
    private TextView btn_next;
    /**
     * 微信
     */
    private ImageView iv_tencent_wechat;
    /**
     * QQ
     */
    private ImageView iv_tencent_qq;
    /**
     * 手机号输入框
     */
    private EditText phone_number;
    /**
     * 密码输入框
     */
    private EditText phone_pwd;
    /**
     * 显示隐藏密码
     */
    private CheckBox checkbox_password;
    /**
     * 删除按钮
     */
    private ImageView delete;
    /**
     * 区号
     */
    private TextView phone_area_code;

    private  UpdateVersionUtils updateVersionUtils = null;

    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    public static void start(Context context) {
        start(context, 0);
    }

    public static void start(Context context, int flag) {
        Router.newIntent(context)
                .to(LoginActivity.class)
                .addFlag(flag)
                .launch();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInit(this, "登陆", true);
        updateVersionUtils = new UpdateVersionUtils();
        preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
        EditText phoneNumber = findViewById(R.id.phone_number);
        EditText phonePwd = findViewById(R.id.phone_pwd);
        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        tv_forget = findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        iv_tencent_wechat = findViewById(R.id.iv_tencent_wechat);
        iv_tencent_wechat.setOnClickListener(this);
        iv_tencent_qq = findViewById(R.id.iv_tencent_qq);
        iv_tencent_qq.setOnClickListener(this);
        checkbox_password = findViewById(R.id.checkbox_password);
        checkbox_password.setOnClickListener(this);
        phone_number = findViewById(R.id.phone_number);
        phone_pwd = findViewById(R.id.phone_pwd);
        phone_area_code = findViewById(R.id.phone_area_code);
        phone_area_code.setOnClickListener(this);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);
        phoneNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    KeyboardUtil.showKeyboard(phonePwd);
                }
                return true;
            }
        });

        checkbox_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    phone_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);// 输入为密码且可见
                }else {
                    phone_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);// 设置文本类密码（默认不可见），这两个属性必须同时设置
                }
            }
        });

        phone_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length()>0){
                        delete.setVisibility(View.VISIBLE);
                    }else {
                        delete.setVisibility(View.INVISIBLE);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phonePwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    //调用登陆接口
                    finish();
                }
                return true;
            }
        });

    }

    @Override
    public void showLoading() {
        DialogLoading.getInstance().showDialog(this, true);
    }

    @Override
    public void hideLoading() {
        DialogLoading.getInstance().closeDialog();
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
        OkGo.getInstance().cancelAll(); //取消OkHttpClient的全部请求
        OkGo.cancelAll(new OkHttpClient());
        OkGo.cancelTag(new OkHttpClient(),"且指定tag");
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:
                RegisterActivity.start(this);
                finish();
                break;
            case R.id.tv_forget:

                break;
            case R.id.btn_next:
                String deviceid = "35" +
                        Build.BOARD.length()%10+
                        Build.BRAND.length()%10 +
                        Build.CPU_ABI.length()%10 +
                        Build.DEVICE.length()%10 +
                        Build.DISPLAY.length()%10 +
                        Build.HOST.length()%10 +
                        Build.ID.length()%10 +
                        Build.MANUFACTURER.length()%10 +
                        Build.MODEL.length()%10 +
                        Build.PRODUCT.length()%10 +
                        Build.TAGS.length()%10 +
                        Build.TYPE.length()%10 +
                        Build.USER.length()%10 ; //13 位
//                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//                 String deviceid = tm.getDeviceId();
                login(phone_number.getText().toString().trim(),phone_pwd.getText().toString().trim());
                break;
            case R.id.iv_tencent_wechat:

                break;
            case R.id.iv_tencent_qq:

                break;
            case R.id.phone_area_code:

                break;
            case R.id.delete:
                phone_number.setText("");
                break;

        }
    }



    @SuppressLint("MissingPermission")
    public void login(String phone,String password) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)){
            ToastUtils.showError(LoginActivity.this,"用户名或密码不能为空!");
            return;
        }

        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("account", phone);
        reqBody.put("password", Md5.strToMd5Low32(password));
        LogUtil.e("aaaaaaaaaaaaaa"+reqBody.toString());
        updateVersionUtils.postByName(Global.LOGIN_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("LoginActivity","result++++++++++++++++"+result+"  "+getPackageName());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    Gson gson = new Gson();
                    ABaseService.loginInfo = gson.fromJson(result, LoginInfo.class);
                    String jsonInfo=gson.toJson(ABaseService.loginInfo); //将对象转换成Json

                    if (code.equals("success")){
                        ToastUtils.show(LoginActivity.this,jsonObject.getString("msg"));
                        editor.putBoolean("islogin",true);
                        editor.putString("token",ABaseService.loginInfo.getToken());
                        editor.putString("userinfo",jsonInfo);
                        editor.commit();
                        ABaseService.islogin = true;
//                        MainActivity.start(LoginActivity.this);
                        setResult(Activity.RESULT_OK);
                        finish();
                    }else{
                        ToastUtils.showError(LoginActivity.this,jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("LoginActivity","error++++++++++++++++"+error);
            }
        });
    }
}
