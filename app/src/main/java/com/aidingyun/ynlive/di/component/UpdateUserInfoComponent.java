package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.UpdateUserInfoModule;

import com.jess.arms.di.scope.ActivityScope;
import com.aidingyun.ynlive.mvp.ui.activity.account.UpdateUserInfoActivity;

@ActivityScope
@Component(modules = UpdateUserInfoModule.class, dependencies = AppComponent.class)
public interface UpdateUserInfoComponent {
    void inject(UpdateUserInfoActivity activity);
}