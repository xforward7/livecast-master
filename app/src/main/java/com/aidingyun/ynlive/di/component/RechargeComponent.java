package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.RechargeModule;

import com.jess.arms.di.scope.ActivityScope;
import com.aidingyun.ynlive.mvp.ui.activity.account.RechargeActivity;

@ActivityScope
@Component(modules = RechargeModule.class, dependencies = AppComponent.class)
public interface RechargeComponent {
    void inject(RechargeActivity activity);
}