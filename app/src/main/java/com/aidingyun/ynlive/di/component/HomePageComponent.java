package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.HomePageModule;

import com.jess.arms.di.scope.FragmentScope;
import com.aidingyun.ynlive.mvp.ui.fragment.HomePageFragment;

@FragmentScope
@Component(modules = HomePageModule.class, dependencies = AppComponent.class)
public interface HomePageComponent {
    void inject(HomePageFragment fragment);
}