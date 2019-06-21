package com.aidingyun.ynlive.component.app;

import android.app.Activity;
import android.content.Context;

import com.aidingyun.ynlive.BuildConfig;

import java.util.Map;

public class GlobalContext {

    private static Context sContext = null;

    private static Activity mCurrActivity = null;

    private static boolean isBackground = false;

    private static Map<String, String> requestParms;

    public static Map<String, String> getRequestParms() {
        return requestParms;
    }

    public static void setRequestParms(Map<String, String> options) {
        requestParms = options;
    }

    protected static void setContext(Context context) {
        sContext = context.getApplicationContext();
    }

    public static Context getAppContext() {
        return sContext;
    }

    public static Activity getCurrActivity() {
        return mCurrActivity;
    }

    public static void setCurrActivity(Activity activity) {
        mCurrActivity = activity;
    }

    protected static void setBackground(boolean background) {
        isBackground = background;
    }

    public static boolean isBackground() {
        return isBackground;
    }

    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    public static String getChannel() {
        return BuildConfig.Channel;
    }

    public static String getBuildInfo() {
        return BuildConfig.BuildTime;
    }

    public static String getBuildDate() {
        return BuildConfig.BuildDate;
    }

}
