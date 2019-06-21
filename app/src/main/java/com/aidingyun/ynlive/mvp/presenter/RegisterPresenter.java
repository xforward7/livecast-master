package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.RegisterContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class RegisterPresenter extends BasePresenter<RegisterContract.Model, RegisterContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;


    @Inject
    public RegisterPresenter(RegisterContract.Model model, RegisterContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
