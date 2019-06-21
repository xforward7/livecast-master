package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.ExpertContract;
import com.aidingyun.ynlive.mvp.model.ExpertModel;


@Module
public class ExpertModule {
    private ExpertContract.View view;

    /**
     * 构建ExpertModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ExpertModule(ExpertContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ExpertContract.View provideExpertView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    ExpertContract.Model provideExpertModel(ExpertModel model) {
        return model;
    }
}