package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.RechargeContract;
import com.aidingyun.ynlive.mvp.model.RechargeModel;


@Module
public class RechargeModule {
    private RechargeContract.View view;

    /**
     * 构建RechargeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public RechargeModule(RechargeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RechargeContract.View provideRechargeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RechargeContract.Model provideRechargeModel(RechargeModel model) {
        return model;
    }
}