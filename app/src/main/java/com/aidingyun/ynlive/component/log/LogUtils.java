package com.aidingyun.ynlive.component.log;

import android.content.Context;

public class LogUtils {
    private static LogProxy sLogger;

    public static void init(Context context) {
        sLogger = new LoggerImpl(context.getApplicationContext());
    }

    public static void flush() {
        if (sLogger != null)
            sLogger.flush();
    }

    public static void v(String tag, String message) {
        v(tag, message, null);
    }

    public static void v(String tag, String message, Throwable t) {
        if (sLogger != null) {
            sLogger.v(tag, message, t);
        } else {
            android.util.Log.v(tag, message, t);
        }
    }

    public static void d(String tag, String message) {
        d(tag, message, null);
    }

    public static void d(String tag, String message, Throwable t) {
        if (sLogger != null) {
            sLogger.d(tag, message, t);
        } else {
            android.util.Log.d(tag, message, t);
        }
    }

    public static void i(String tag, String message) {
        i(tag, message, null);
    }

    public static void i(String tag, String message, Throwable t) {
        if (sLogger != null) {
            sLogger.i(tag, message, t);
        } else {
            android.util.Log.i(tag, message, t);
        }
    }

    public static void w(String tag, String message) {
        w(tag, message, null);
    }

    public static void w(String tag, String message, Throwable t) {
        if (sLogger != null) {
            sLogger.w(tag, message, t);
        } else {
            android.util.Log.w(tag, message, t);
        }
    }

    public static void e(String tag, String message) {
        e(tag, message, null);
    }

    public static void e(String tag, String message, Throwable t) {
        if (sLogger != null) {
            sLogger.e(tag, message, t);
        } else {
            android.util.Log.e(tag, message, t);
        }
    }
}
