package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.StandpointContract;
import com.aidingyun.ynlive.mvp.model.StandpointModel;


@Module
public class StandpointModule {
    private StandpointContract.View view;

    /**
     * 构建StandpointModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public StandpointModule(StandpointContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    StandpointContract.View provideStandpointView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    StandpointContract.Model provideStandpointModel(StandpointModel model) {
        return model;
    }
}