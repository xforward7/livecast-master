package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.BindAccountContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class BindAccountPresenter extends BasePresenter<BindAccountContract.Model, BindAccountContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;


    @Inject
    public BindAccountPresenter(BindAccountContract.Model model, BindAccountContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;

    }
}
