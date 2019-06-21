package com.aidingyun.core.widget;

import android.app.Dialog;
import android.content.Context;


/**
 * Created by Mango
 * on 2017/4/11.
 */

public class DialogLoading {

    public static Dialog dialog;

    private DialogLoading() {

    }

    public static DialogLoading getInstance() {
        return DialogHolder.instantce;
    }

    public void showDialog(Context context, boolean cancelable) {
        showDialog(context, "请稍候...", cancelable);
    }

    public void showDialog(Context context, String msg, boolean cancelable) {
        try {
            if (dialog == null) {
                dialog = ProgressFactory.newLoading(context, msg);
            } else {
                closeDialog();
                dialog = ProgressFactory.newLoading(context, msg);
            }
            dialog.setCancelable(cancelable);
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDialog() {
        try {
            if (dialog != null) {
                ProgressFactory.closeDialog(dialog);
                dialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class DialogHolder {
        static DialogLoading instantce = new DialogLoading();
    }
}
