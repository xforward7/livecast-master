package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.SortContract;
import com.aidingyun.ynlive.mvp.model.SortModel;


@Module
public class SortModule {
    private SortContract.View view;

    /**
     * 构建SortModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SortModule(SortContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    SortContract.View provideSortView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    SortContract.Model provideSortModel(SortModel model) {
        return model;
    }
}