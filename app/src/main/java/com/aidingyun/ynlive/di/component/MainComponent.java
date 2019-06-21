package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;
import com.aidingyun.ynlive.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}