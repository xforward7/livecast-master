package com.aidingyun.ynlive.component.app;


import android.content.Context;

import com.aidingyun.ynlive.app.utils.NetworkState;
import com.aidingyun.ynlive.component.log.LogUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

public class GlobalAppProxy implements IAppProxy {
    private final String TAG = getClass().getSimpleName();

    private static GlobalAppProxy mInstance = null;
    private static final byte[] INSTANCE_LOCK = new byte[0];

    public static GlobalAppProxy getInstance() {
        if (mInstance == null) {
            synchronized (INSTANCE_LOCK) {
                if (mInstance == null) {
                    mInstance = new GlobalAppProxy();
                }
            }
        }
        return mInstance;
    }

    private GlobalAppProxy() {

    }

    @Override
    public void onCreate(Context context) {
        GlobalContext.setContext(context);

        LogUtils.init(context);
        LogUtils.i(TAG, "onCreate");
        NetworkState.getInstance().setContext(context);

        Fresco.initialize(context);
        Stetho.initializeWithDefaults(context);
    }

    @Override
    public void onCreateMainProcess(final Context context) {
        LogUtils.i(TAG, "onCreateMainProcess");
    }

    @Override
    public void onTerminate(Context context) {
        LogUtils.i(TAG, "onTerminate");
        LogUtils.flush();
    }

    @Override
    public void onLowMemory() {
        LogUtils.i(TAG, "onTerminate");
    }

    @Override
    public boolean isBackground() {
        return GlobalContext.isBackground();
    }

    @Override
    public boolean isLockScreen() {
        return false;
    }

    @Override
    public void onEnterForeground() {
        LogUtils.i(TAG, "onEnterForeground");
        GlobalContext.setBackground(false);
    }

    @Override
    public void onEnterBackground() {
        LogUtils.i(TAG, "onEnterBackground");
        GlobalContext.setBackground(true);
    }

    @Override
    public void onScreenOn() {
        LogUtils.i(TAG, "onScreenOn");
    }

    @Override
    public void onScreenOff() {
        LogUtils.i(TAG, "onScreenOff");
    }
}
