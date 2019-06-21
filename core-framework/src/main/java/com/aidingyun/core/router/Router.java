package com.aidingyun.core.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wanglei on 2016/11/29.
 */
public class Router {

    public static final int RES_NONE = -1;
    private static RouterCallback callback;
    private Intent intent;
    private Context from;
    private Class<?> to;
    private Bundle data;
    private ActivityOptionsCompat options;
    private int requestCode = -1;
    private int enterAnim = 0;
    private int exitAnim = 0;

    private Router() {
        intent = new Intent();
    }

    public static Router newIntent(Context context) {
        Router router = new Router();
        router.from = context;
        return router;
    }

    public static void pop(Activity activity) {
        activity.finish();
    }

    public static void setCallback(RouterCallback callback) {
        Router.callback = callback;
    }

    public Router to(Class<?> to) {
        this.to = to;
        return this;
    }

    public Router addFlags(int flags) {
        if (intent != null) {
            intent.addFlags(flags);
        }
        return this;
    }

    public Router data(Bundle data) {
        this.data = data;
        return this;
    }

    public Router putBundle(@Nullable String key, Bundle bundle) {
        getBundleData().putBundle(key, bundle);
        return this;
    }

    public Router putByte(@Nullable String key, byte value) {
        getBundleData().putByte(key, value);
        return this;
    }

    public Router putChar(@Nullable String key, char value) {
        getBundleData().putChar(key, value);
        return this;
    }

    public Router putInt(@Nullable String key, int value) {
        getBundleData().putInt(key, value);
        return this;
    }

    public Router putString(@Nullable String key, String value) {
        getBundleData().putString(key, value);
        return this;
    }

    public Router putShort(@Nullable String key, short value) {
        getBundleData().putShort(key, value);
        return this;
    }

    public Router putFloat(@Nullable String key, float value) {
        getBundleData().putFloat(key, value);
        return this;
    }

    public Router putDouble(@Nullable String key, double value) {
        getBundleData().putDouble(key, value);
        return this;
    }

    public Router putCharSequence(@Nullable String key, @Nullable CharSequence value) {
        getBundleData().putCharSequence(key, value);
        return this;
    }

    public Router putParcelable(@Nullable String key, @Nullable Parcelable value) {
        getBundleData().putParcelable(key, value);
        return this;
    }

    public Router putLong(@Nullable String key, long value) {
        getBundleData().putLong(key, value);
        return this;
    }

    public Router putBoolean(@Nullable String key, boolean value) {
        getBundleData().putBoolean(key, value);
        return this;
    }

    public Router putParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
        getBundleData().putParcelableArray(key, value);
        return this;
    }

    public Router putParcelableArrayList(@Nullable String key,
                                         @Nullable ArrayList<? extends Parcelable> value) {
        getBundleData().putParcelableArrayList(key, value);
        return this;
    }

    public Router putIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        getBundleData().putIntegerArrayList(key, value);
        return this;
    }

    public Router putStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        getBundleData().putStringArrayList(key, value);
        return this;
    }

    public Router putCharSequenceArrayList(@Nullable String key,
                                           @Nullable ArrayList<CharSequence> value) {
        getBundleData().putCharSequenceArrayList(key, value);
        return this;
    }

    public Router putSerializable(@Nullable String key, @Nullable Serializable value) {
        getBundleData().putSerializable(key, value);
        return this;
    }

    public Router addFlag(@Nullable int flag) {
        if (flag == 0) {
            return this;
        }
        intent.addFlags(flag);
        return this;
    }

    public Router options(ActivityOptionsCompat options) {
        this.options = options;
        return this;
    }

    public Router requestCode(int requestCode) {
        if (from instanceof Activity) {
            this.requestCode = requestCode;
            return this;
        } else {
            throw new UnsupportedOperationException(" 'from' must be a activity ,when you use requestcode");
        }
    }

    public Router anim(int enterAnim, int exitAnim) {
        this.enterAnim = enterAnim;
        this.exitAnim = exitAnim;
        return this;
    }

    public void launch() {
        try {
            if (intent != null && from != null && to != null) {

                if (callback != null) {
                    callback.onBefore(from, to);
                }

                intent.setClass(from, to);

                intent.putExtras(getBundleData());

                if (options == null) {
                    if (requestCode < 0) {
                        if (!(from instanceof Activity)) {
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            from.startActivity(intent);
                        } else {
                            from.startActivity(intent);
                            if (enterAnim > 0 && exitAnim > 0) {
                                ((Activity) from).overridePendingTransition(enterAnim, exitAnim);
                            }
                        }
                    } else {
                        ((Activity) from).startActivityForResult(intent, requestCode);
                        if (enterAnim > 0 && exitAnim > 0) {
                            ((Activity) from).overridePendingTransition(enterAnim, exitAnim);
                        }
                    }
                } else {
                    if (requestCode < 0) {
                        if (!(from instanceof Activity)) {
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }
                        ActivityCompat.startActivity(from, intent, options.toBundle());
                    } else {
                        ActivityCompat.startActivityForResult((Activity) from, intent, requestCode, options.toBundle());
                    }
                }

                if (callback != null) {
                    callback.onNext(from, to);
                }
            }
        } catch (Throwable throwable) {
            if (callback != null) {
                callback.onError(from, to, throwable);
            }
        }
    }

    private Bundle getBundleData() {
        if (data == null) {
            data = new Bundle();
        }
        return data;
    }
}
