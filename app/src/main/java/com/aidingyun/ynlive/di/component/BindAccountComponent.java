package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.BindAccountModule;

import com.jess.arms.di.scope.ActivityScope;
import com.aidingyun.ynlive.mvp.ui.activity.account.BindAccountActivity;

@ActivityScope
@Component(modules = BindAccountModule.class, dependencies = AppComponent.class)
public interface BindAccountComponent {
    void inject(BindAccountActivity activity);
}