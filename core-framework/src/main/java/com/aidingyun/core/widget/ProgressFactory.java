package com.aidingyun.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aidingyun.core.R;


/**
 * Created by xiaoys on 2016/11/15.
 */

public class ProgressFactory {


    /**
     * private constructor
     */
    private ProgressFactory() {

    }

    public static Dialog newLoading(Context context, String tips) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = inflate.findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = inflate.findViewById(R.id.tipTextView);// 提示文字
        // 进度条
        ProgressBar progressbar = inflate.findViewById(R.id.progressbar);
        // 提示文字
        if (!TextUtils.isEmpty(tips)) {
            tipTextView.setText(tips);// 设置加载信息
        }
        AnimationSet animation = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
        progressbar.startAnimation(animation);

        Dialog loadingDialog = new Dialog(context, R.style.DialogLoadingStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面  */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        //为Window设置动画
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        return loadingDialog;
    }

    /**
     * 关闭dialog
     * <p>
     * http://blog.csdn.net/qq_21376985
     *
     * @param dialog
     */
    public static Dialog closeDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            return dialog;
        }
        return null;
    }
}