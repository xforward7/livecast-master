package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.PayContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class PayPresenter extends BasePresenter<PayContract.Model, PayContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public PayPresenter(PayContract.Model model, PayContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
