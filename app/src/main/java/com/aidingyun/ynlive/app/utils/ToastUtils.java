package com.aidingyun.ynlive.app.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aidingyun.ynlive.R;
import com.jess.arms.utils.ArmsUtils;

import me.drakeet.support.toast.ToastCompat;

public class ToastUtils {

    private static Handler mMainHandler = new Handler(Looper.getMainLooper());

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    private static void doShowToast(Context context, final CharSequence text) {
        ToastCompat toastCompat = ToastCompat.makeText(context, text, Toast.LENGTH_SHORT)
                .setBadTokenListener(toast -> {
                    ArmsUtils.snackbarText(text.toString());
                });
        toastCompat.setGravity(Gravity.CENTER, 0, 0);
        View inflate = LayoutInflater.from(context).inflate(R.layout.widget_toast, null);
        TextView textView = inflate.findViewById(R.id.tv_toast_text);
        textView.setText(text);
        toastCompat.setDuration(Toast.LENGTH_SHORT);
        toastCompat.setView(inflate);
        toastCompat.show();
    }

    public static void show(final Context context, final CharSequence text) {
        if (isMainThread()) {
            doShowToast(context, text);
        } else {
            mMainHandler.post(() -> doShowToast(context, text));
        }
    }

    public static void showError(final Context context, final CharSequence text) {
        if (isMainThread()) {
            doShowToast(context, text);
        } else {
            mMainHandler.post(() -> doShowToast(context, text));
        }
    }
}
