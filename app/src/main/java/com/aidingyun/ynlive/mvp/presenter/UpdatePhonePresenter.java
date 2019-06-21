package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.UpdatePhoneContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class UpdatePhonePresenter extends BasePresenter<UpdatePhoneContract.Model, UpdatePhoneContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public UpdatePhonePresenter(UpdatePhoneContract.Model model, UpdatePhoneContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
