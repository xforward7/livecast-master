package com.aidingyun.ynlive.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.aidingyun.ynlive.mvp.contract.UserPageContract;
import com.aidingyun.ynlive.mvp.model.UserPageModel;


@Module
public class UserPageModule {
    private UserPageContract.View view;

    /**
     * 构建UserPageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public UserPageModule(UserPageContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    UserPageContract.View provideUserPageView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    UserPageContract.Model provideUserPageModel(UserPageModel model) {
        return model;
    }
}