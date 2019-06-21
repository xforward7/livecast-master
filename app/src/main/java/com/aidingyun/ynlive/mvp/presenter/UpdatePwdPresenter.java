package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.UpdatePwdContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class UpdatePwdPresenter extends BasePresenter<UpdatePwdContract.Model, UpdatePwdContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public UpdatePwdPresenter(UpdatePwdContract.Model model, UpdatePwdContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
