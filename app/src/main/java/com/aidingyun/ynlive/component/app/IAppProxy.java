package com.aidingyun.ynlive.component.app;

import android.content.Context;

public interface IAppProxy {
    void onCreate(final Context context);

    void onCreateMainProcess(final Context context);

    void onTerminate(final Context context);

    void onLowMemory();

    boolean isBackground();

    boolean isLockScreen();

    void onEnterForeground();

    void onEnterBackground();

    void onScreenOn();

    void onScreenOff();
}
