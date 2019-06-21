package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.PayModule;

import com.jess.arms.di.scope.ActivityScope;
import com.aidingyun.ynlive.mvp.ui.activity.account.PayActivity;

@ActivityScope
@Component(modules = PayModule.class, dependencies = AppComponent.class)
public interface PayComponent {
    void inject(PayActivity activity);
}