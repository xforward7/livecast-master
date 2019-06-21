package com.aidingyun.ynlive.mvp.presenter;

import com.aidingyun.ynlive.mvp.contract.RecommendContract;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@FragmentScope
public class RecommendPresenter extends BasePresenter<RecommendContract.Model, RecommendContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;


    @Inject
    public RecommendPresenter(RecommendContract.Model model, RecommendContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;

    }
}
