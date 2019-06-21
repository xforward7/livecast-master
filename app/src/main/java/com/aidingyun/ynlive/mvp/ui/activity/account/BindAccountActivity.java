package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.aidingyun.ynlive.di.component.DaggerBindAccountComponent;
import com.aidingyun.ynlive.di.module.BindAccountModule;
import com.aidingyun.ynlive.mvp.contract.BindAccountContract;
import com.aidingyun.ynlive.mvp.presenter.BindAccountPresenter;

import com.aidingyun.ynlive.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class BindAccountActivity extends BaseActivity<BindAccountPresenter> implements BindAccountContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBindAccountComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .bindAccountModule(new BindAccountModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_bind_account;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
}
