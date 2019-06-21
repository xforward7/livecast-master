package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.mvp.ui.activity.HobbyActivity;
import com.facebook.stetho.common.LogUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 大王老师
     */
    private TextView mTvUpdateInfo;
    /**
     * 手机号码
     */
    private TextView mTvUpdatePhone;
    /**
     * 密码修改
     */
    private TextView mTvUpdatePwd;
    /**
     * 修改兴趣
     */
    private TextView mTvUpdateHoddy;
    /**
     * 主播实名认证
     */
    private TextView mTvNameCheck;

    private TextView mTvLogout;

    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_personal_center;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInit(this, "个人中心", true);
        preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
        mTvUpdateInfo = (TextView) findViewById(R.id.tv_update_info);
        mTvUpdateInfo.setOnClickListener(this);
        mTvUpdatePhone = (TextView) findViewById(R.id.tv_update_phone);
        mTvUpdatePhone.setOnClickListener(this);
        mTvUpdatePwd = (TextView) findViewById(R.id.tv_update_pwd);
        mTvUpdatePwd.setOnClickListener(this);
        mTvUpdateHoddy = (TextView) findViewById(R.id.tv_update_hoddy);
        mTvUpdateHoddy.setOnClickListener(this);
        mTvNameCheck = (TextView) findViewById(R.id.tv_name_check);
        mTvNameCheck.setOnClickListener(this);
        mTvLogout = (TextView) findViewById(R.id.tv_logout_btn);
        mTvLogout.setOnClickListener(this);

        setData();
    }

    private void setData() {
        mTvUpdateInfo.setText(ABaseService.loginInfo.getUsername());
        mTvUpdatePhone.setText(ABaseService.loginInfo.getPhone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_update_info:
                UpdateUserInfoActivity.start(this);
                break;
            case R.id.tv_update_phone:
                UpdatePhoneActivity.start(this);
                break;
            case R.id.tv_update_pwd:
                UpdatePwdActivity.start(this);
                break;
            case R.id.tv_update_hoddy:
                startActivity(new Intent(this,HobbyActivity.class));
                break;
            case R.id.tv_name_check:
                ToastUtils.show(this, "请使用网页版认证");
                break;
            case R.id.tv_logout_btn:
                editor.putBoolean("islogin",false);
                editor.putString("token","");
                editor.putString("userinfo","");
                editor.commit();
                ABaseService.loginInfo = null;
                ABaseService.islogin = false;
                ABaseService.token = "";
//                ABaseService.siteid = "";
                finish();
                break;
            default:
                break;
        }
    }


}
