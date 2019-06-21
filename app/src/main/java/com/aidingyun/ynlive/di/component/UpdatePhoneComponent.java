package com.aidingyun.ynlive.di.component;

import com.aidingyun.ynlive.di.module.UpdatePhoneModule;
import com.aidingyun.ynlive.mvp.ui.activity.account.UpdatePhoneActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = UpdatePhoneModule.class, dependencies = AppComponent.class)
public interface UpdatePhoneComponent {
    void inject(UpdatePhoneActivity activity);
}