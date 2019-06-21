package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.UpdateUserInfoContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class UpdateUserInfoPresenter extends BasePresenter<UpdateUserInfoContract.Model, UpdateUserInfoContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public UpdateUserInfoPresenter(UpdateUserInfoContract.Model model, UpdateUserInfoContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
