package com.aidingyun.ynlive.mvp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

public class ActionSheetDialog {

    private Context context;
    private Dialog dialog;
    private TextView txtTitle;
    private TextView txtCancel;
    private LinearLayout llayoutContent;
    private ScrollView slayoutContent;
    private boolean showTitle = false;
    private List<SheetItem> sheetItemList;

    public ActionSheetDialog(Context context) {
        this.context = context;
    }

    public ActionSheetDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.toast_view_actionsheet, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(ArmsUtils.getScreenWidth(context));

        // 获取自定义Dialog布局中的控件
        slayoutContent = view.findViewById(R.id.sLayout_content);
        llayoutContent = view.findViewById(R.id.lLayout_content);
        txtTitle = view.findViewById(R.id.txt_title);
        txtCancel = view.findViewById(R.id.txt_cancel);
        txtCancel.setOnClickListener(v -> dialog.dismiss());


        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        dialogWindow.setDimAmount(0.4f);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    public ActionSheetDialog setTitle(String title) {
        showTitle = true;
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(title);
        return this;
    }

    public ActionSheetDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public ActionSheetDialog addSheetItem(String strItem, SheetItemColor color, OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }

    /**
     * 设置条目布局
     */
    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }

        int size = sheetItemList.size();

        if (size >= 7) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) slayoutContent.getLayoutParams();
            params.height = ArmsUtils.getScreenHeidth(context) / 2;
            slayoutContent.setLayoutParams(params);
        }

        // 循环添加条目
        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.name;
            SheetItemColor color = sheetItem.color;
            final OnSheetItemClickListener listener = sheetItem.itemClickListener;

            TextView textView = new TextView(context);
            textView.setText(strItem);
            textView.setTextSize(16);
            textView.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            textView.setBackgroundResource(R.drawable.bg_txt_stroke_white);
            textView.setGravity(Gravity.CENTER);

            // 背景图片
            if (size == 1) {
                if (showTitle) {
                    textView.setBackgroundResource(R.drawable.bg_txt_stroke_white_bottom);
                } else {
                    textView.setBackgroundResource(R.drawable.bg_txt_stroke_white);
                }
            } else {
                if (showTitle) {
                    if (i >= 1 && i < size) {
                        textView.setBackgroundResource(R.drawable.bg_txt_stroke_white_middle);
                    } else {
                        textView.setBackgroundResource(R.drawable.bg_txt_stroke_white_bottom);
                    }
                } else {
                    if (i == 1) {
                        textView.setBackgroundResource(R.drawable.bg_txt_stroke_white_top);
                    } else if (i < size) {
                        textView.setBackgroundResource(R.drawable.bg_txt_stroke_white_middle);
                    } else {
                        textView.setBackgroundResource(R.drawable.bg_txt_stroke_white_bottom);
                    }
                }
            }

            // 字体颜色
            if (color == null) {
                textView.setTextColor(Color.parseColor(SheetItemColor.Black.getName()));
            } else {
                textView.setTextColor(Color.parseColor(color.getName()));
            }

            // 高度
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            lp.leftMargin = ArmsUtils.dip2px(context, 15);
            lp.rightMargin = ArmsUtils.dip2px(context, 15);
            textView.setLayoutParams(lp);

            // 点击事件
            textView.setOnClickListener(v -> {
                listener.onClick(index);
                dialog.dismiss();
            });

            llayoutContent.addView(textView);
        }
    }

    public void show() {
        setSheetItems();
        dialog.show();
    }

    public interface OnSheetItemClickListener {

        void onClick(int which);
    }

    public class SheetItem {

        String name;
        OnSheetItemClickListener itemClickListener;
        SheetItemColor color;

        public SheetItem(String name, SheetItemColor color, OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
    }

    public enum SheetItemColor {
        Black("#000000"), Red("#FD4A2E"), ;

        private String name;

        SheetItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

