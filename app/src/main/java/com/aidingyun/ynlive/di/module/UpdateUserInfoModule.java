package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.UpdateUserInfoContract;
import com.aidingyun.ynlive.mvp.model.UpdateUserInfoModel;


@Module
public class UpdateUserInfoModule {
    private UpdateUserInfoContract.View view;

    /**
     * 构建UpdateUserInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public UpdateUserInfoModule(UpdateUserInfoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UpdateUserInfoContract.View provideUpdateUserInfoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UpdateUserInfoContract.Model provideUpdateUserInfoModel(UpdateUserInfoModel model) {
        return model;
    }
}