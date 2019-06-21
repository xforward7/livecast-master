package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.RecommendContract;
import com.aidingyun.ynlive.mvp.model.RecommendModel;


@Module
public class RecommendModule {
    private RecommendContract.View view;

    /**
     * 构建RecommendModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public RecommendModule(RecommendContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    RecommendContract.View provideRecommendView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    RecommendContract.Model provideRecommendModel(RecommendModel model) {
        return model;
    }
}