package com.aidingyun.ynlive.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.jess.arms.utils.ArmsUtils;

public class NavigateItem {
    public int mActivityId;
    private String mName;
    private Drawable mIcon;
    private Drawable mIconFocus;

    private View mTableView;
    private TextView mTitleView;
    private ImageView mIconView;
    private ImageView dotView;
    private TextView pointText;

    public NavigateItem(int fragmentId, Drawable icon, Drawable iconFocus, Context mContext) {
        this(fragmentId, "", icon, iconFocus, mContext);
    }

    public NavigateItem(int activityId, String name, Drawable icon, Drawable iconFocus, Context mContext) {
        mActivityId = activityId;
        mName = name;
        mIcon = icon;
        mIconFocus = iconFocus;
        mTableView = initTabItem(mContext);
    }

    public View getItemView() {
        return mTableView;
    }

    public void setFocus(boolean isFocus, Context mContext) {
        mIconView.setImageDrawable(isFocus && mIconFocus != null ? mIconFocus : mIcon);
        int textColorId = isFocus ? R.color.colorPrimary : R.color.colorPrompt;
        mTitleView.setTextColor(mContext.getResources().getColor(textColorId));
    }

    private View initTabItem(Context mContext) {
        View inflate = ArmsUtils.inflate(mContext, R.layout.widget_nav_item);
        mIconView = inflate.findViewById(R.id.nav_img);
        mIconView.setImageDrawable(mIcon);
        mTitleView = inflate.findViewById(R.id.nav_text);
        if (TextUtils.isEmpty(mName)) {
            mTitleView.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mIconView.getLayoutParams();
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            mIconView.setLayoutParams(layoutParams);

        } else {
            mTitleView.setTextColor(mTitleView.getResources().getColor(R.color.colorPrompt));
            mTitleView.setTextSize(10);
            mTitleView.setText(mName);
            mTitleView.setVisibility(View.VISIBLE);
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        inflate.setLayoutParams(params);
        return inflate;
    }

    public boolean dismissPointView() {
        if (pointText != null && pointText.getVisibility() == View.VISIBLE) {
            pointText.setVisibility(View.GONE);
            return true;
        }
        return false;
    }
// 图标右上角数量
    public void setPointView(int messageNum) {
        pointText = mTableView.findViewById(R.id.point);
        mTableView.findViewById(R.id.dot).setVisibility(View.GONE);
        pointText.setVisibility(View.VISIBLE);
        pointText.setText(messageNum > 99 ? "   99+  " : messageNum > 9 ? "   " + messageNum + "   " : messageNum + "");
        pointText.setTag(messageNum);
    }

    public boolean dismissDotView() {
        if (dotView != null && dotView.getVisibility() == View.VISIBLE) {
            dotView.setVisibility(View.GONE);
            return true;
        }
        return false;
    }

    public void setDotView() {
        dotView = mTableView.findViewById(R.id.dot);
        mTableView.findViewById(R.id.point).setVisibility(View.GONE);
        dotView.setVisibility(View.VISIBLE);
    }


}