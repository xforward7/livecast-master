package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.UserPageModule;

import com.jess.arms.di.scope.FragmentScope;
import com.aidingyun.ynlive.mvp.ui.fragment.UserPageFragment;

@FragmentScope
@Component(modules = UserPageModule.class, dependencies = AppComponent.class)
public interface UserPageComponent {
    void inject(UserPageFragment fragment);
}