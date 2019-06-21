package com.aidingyun.ynlive.di.component;

import com.aidingyun.ynlive.di.module.LoginModule;
import com.aidingyun.ynlive.mvp.ui.activity.account.LoginActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}