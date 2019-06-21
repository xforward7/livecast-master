package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.BindAccountContract;
import com.aidingyun.ynlive.mvp.model.BindAccountModel;


@Module
public class BindAccountModule {
    private BindAccountContract.View view;

    /**
     * 构建BindAccountModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BindAccountModule(BindAccountContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BindAccountContract.View provideBindAccountView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BindAccountContract.Model provideBindAccountModel(BindAccountModel model) {
        return model;
    }
}