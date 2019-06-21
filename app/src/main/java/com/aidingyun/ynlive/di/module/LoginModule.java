package com.aidingyun.ynlive.di.module;

import com.aidingyun.ynlive.mvp.contract.LoginContract;
import com.aidingyun.ynlive.mvp.model.entity.LoginModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class LoginModule {
    private LoginContract.View view;

    /**
     * 构建LoginModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope


    @Provides
    LoginContract.View provideLoginView() {
        return this.view;
    }

    @ActivityScope

    @Provides
    LoginContract.Model provideLoginModel(LoginModel model) {
        return model;
    }
}