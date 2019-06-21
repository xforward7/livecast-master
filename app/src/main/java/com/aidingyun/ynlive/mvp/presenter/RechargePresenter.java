package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.RechargeContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class RechargePresenter extends BasePresenter<RechargeContract.Model, RechargeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public RechargePresenter(RechargeContract.Model model, RechargeContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
