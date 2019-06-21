package com.aidingyun.ynlive.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.aidingyun.ynlive.di.module.SortModule;

import com.jess.arms.di.scope.FragmentScope;
import com.aidingyun.ynlive.mvp.ui.fragment.SortFragment;

@FragmentScope
@Component(modules = SortModule.class, dependencies = AppComponent.class)
public interface SortComponent {
    void inject(SortFragment fragment);
}