package com.aidingyun.ynlive.mvp.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.jess.arms.utils.ArmsUtils;

public class DialogUtils {

    public static AlertDialog showAlertDialog(Context context, String title, String positiveText, final DialogInterface.OnClickListener positiveListener) {
        return showAlertDialog(context, title, null, positiveText, positiveListener, "取消", null);
    }

    public static AlertDialog showAlertDialog(Context context, String title, String positiveText, final DialogInterface.OnClickListener positiveListener,
                                              String negativeText) {
        return showAlertDialog(context, title, null, positiveText, positiveListener, negativeText, null);
    }

    public static AlertDialog showAlertDialog(Context context, String title, String message, String positiveText, final DialogInterface.OnClickListener positiveListener,
                                              String negativeText, final DialogInterface.OnClickListener negativeListener) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialog);
        View view = View.inflate(context, R.layout.widget_alert_dialog, null);
        builder.setView(view);
        builder.setCancelable(true);

        TextView titleView = view.findViewById(R.id.title);
        if (TextUtils.isEmpty(title)) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
            titleView.setText(title);
        }
        TextView messageView = view.findViewById(R.id.message);
        if (TextUtils.isEmpty(message)) {
            messageView.setVisibility(View.GONE);
        } else {
            messageView.setVisibility(View.VISIBLE);
            messageView.setText(message);
        }
        final AlertDialog dialog = builder.create();

        Button btn_cancel = view.findViewById(R.id.btn_cancel);//取消按钮
        if (TextUtils.isEmpty(negativeText)) {
            view.findViewById(R.id.btn_cancel_layout).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.btn_cancel_layout).setVisibility(View.VISIBLE);
            btn_cancel.setText(negativeText);
        }

        Button btn_comfirm = view.findViewById(R.id.btn_comfirm);//确定按钮
        if (TextUtils.isEmpty(positiveText)) {
            view.findViewById(R.id.btn_comfirm_layout).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.btn_comfirm_layout).setVisibility(View.VISIBLE);
            btn_comfirm.setText(positiveText);
        }
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (negativeListener != null)
                    negativeListener.onClick(dialog, 0);
                dialog.dismiss();
            }
        });
        btn_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positiveListener != null)
                    positiveListener.onClick(dialog, 1);
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ArmsUtils.getScreenWidth(context) - ArmsUtils.dip2px(context, 100), LinearLayout.LayoutParams.WRAP_CONTENT);

        return dialog;
    }
}
