package com.aidingyun.ynlive.di.component;

import com.aidingyun.ynlive.di.module.HobbyModule;
import com.aidingyun.ynlive.mvp.ui.activity.HobbyActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = HobbyModule.class, dependencies = AppComponent.class)
public interface HobbyComponent {
    void inject(HobbyActivity activity);
}