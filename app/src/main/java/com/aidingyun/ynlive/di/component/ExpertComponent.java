package com.aidingyun.ynlive.di.component;

import com.aidingyun.ynlive.di.module.ExpertModule;
import com.aidingyun.ynlive.mvp.ui.fragment.ExpertFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = ExpertModule.class, dependencies = AppComponent.class)
public interface ExpertComponent {
    void inject(ExpertFragment fragment);
}