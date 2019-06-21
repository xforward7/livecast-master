package com.aidingyun.ynlive.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import timber.log.Timber;

/**
 * ================================================
 * 展示 {@link Application.ActivityLifecycleCallbacks} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.w(activity + " - onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Timber.w(activity + " - onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {


        Timber.w(activity + " - onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Timber.w(activity + " - onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Timber.w(activity + " - onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Timber.w(activity + " - onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Timber.w(activity + " - onActivityDestroyed");
    }
}
