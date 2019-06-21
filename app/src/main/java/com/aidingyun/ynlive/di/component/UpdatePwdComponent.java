package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.UpdatePwdModule;

import com.jess.arms.di.scope.ActivityScope;
import com.aidingyun.ynlive.mvp.ui.activity.account.UpdatePwdActivity;

@ActivityScope
@Component(modules = UpdatePwdModule.class, dependencies = AppComponent.class)
public interface UpdatePwdComponent {
    void inject(UpdatePwdActivity activity);
}