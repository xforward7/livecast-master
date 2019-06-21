package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.StandpointModule;

import com.jess.arms.di.scope.FragmentScope;
import com.aidingyun.ynlive.mvp.ui.fragment.StandpointFragment;

@FragmentScope
@Component(modules = StandpointModule.class, dependencies = AppComponent.class)
public interface StandpointComponent {
    void inject(StandpointFragment fragment);
}