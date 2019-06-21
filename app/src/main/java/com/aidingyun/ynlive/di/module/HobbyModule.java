package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.HobbyContract;
import com.aidingyun.ynlive.mvp.model.HobbyModel;


@Module
public class HobbyModule {
    private HobbyContract.View view;

    /**
     * 构建HobbyModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HobbyModule(HobbyContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HobbyContract.View provideHobbyView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HobbyContract.Model provideHobbyModel(HobbyModel model) {
        return model;
    }
}