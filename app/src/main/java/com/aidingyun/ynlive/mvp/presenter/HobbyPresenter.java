package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.HobbyContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class HobbyPresenter extends BasePresenter<HobbyContract.Model, HobbyContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public HobbyPresenter(HobbyContract.Model model, HobbyContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
