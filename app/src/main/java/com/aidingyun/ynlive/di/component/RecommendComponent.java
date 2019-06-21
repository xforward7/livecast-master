package com.aidingyun.ynlive.di.component;

import com.aidingyun.ynlive.di.module.RecommendModule;
import com.aidingyun.ynlive.mvp.ui.fragment.HomePageListFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(HomePageListFragment fragment);
}