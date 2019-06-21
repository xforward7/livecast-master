package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.HomePageContract;
import com.aidingyun.ynlive.mvp.model.HomePageModel;


@Module
public class HomePageModule {
    private HomePageContract.View view;

    /**
     * 构建HomePageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HomePageModule(HomePageContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    HomePageContract.View provideHomePageView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    HomePageContract.Model provideHomePageModel(HomePageModel model) {
        return model;
    }
}