package com.aidingyun.ynlive.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 作者：Leon
 * 时间：2016/12/21 10:32
 * 方法说明
 * <p>
 * 在布局文件中引用
 * <p>
 * <com.leon.lib.settingview.LSettingItem
 * xmlns:app="http://schemas.android.com/apk/res-auto"
 * android:id="@+id/item_one"
 * android:layout_width="match_parent"
 * android:layout_height="wrap_content"
 * leon:leftIcon="@drawable/history"
 * leon:leftText="我的消息"/>
 * 3. 添加单击事件处理
 * <p>
 * LSettingItem mSettingItemOne = (LSettingItem) findViewById(R.id.item_one);
 * mSettingItemOne.setOnSettingItemClick(new LSettingItem.OnLSettingItemClick() {
 *
 * @Override public void onSettingItemClick() {
 * Toast.makeText(getApplicationContext(), "我的消息", Toast.LENGTH_SHORT).show();
 * }
 * });
 * 属性	说明	类型
 * leftText	左侧文字	string
 * leftIcon	左侧图标	integer
 * rightIcon	右侧图标	integer
 * textSize	左侧文字大小	dimension
 * textColor	左侧文字颜色	color
 * isShowUnderLine	是否显示底部分割线	boolean
 * rightStyle	右侧图标风格	enum
 * 右侧图标风格
 * <p>
 * iconShow 显示图标
 * iconHide 隐藏图标
 * iconCheck 显示复选框
 * iconSwitch 显示切换开关
 */
public class SettingItem extends RelativeLayout {
    /*左侧图标*/
    private Drawable mLeftIcon;
    /*右侧图标*/
    private Drawable mRightIcon;
    /*左侧显示文本大小*/
    private int mTextSize;
    /*左侧显示文本颜色*/
    private int mTextColor;
    /*整体根布局view*/
    private View inflate;
    /*根布局*/
    private LinearLayout mRootLayout;
    /*左侧文本控件*/
    private TextView mTvLeftText;
    /*右侧文本控件*/
    private TextView mTvRightText;
    /*上面分割线*/
    private View mTopline;
    /*下面分割线*/
    private View mUnderLine;
    /*左侧图标控件*/
    private ImageView mIvLeftIcon;
    /*右侧图标控件区域,默认展示图标*/
    private RelativeLayout mRightLayout;
    /*右侧图标控件,默认展示图标*/
    private ImageView mIvRightIcon;
    /*右侧图标控件,选择样式图标*/
    private AppCompatCheckBox mRightIconCheck;
    /*右侧图标控件,开关样式图标*/
    private SwitchCompat mRightIconSwitch;
    /*右侧图标控件,进度条样式*/
    private ProgressBar mProgressbar;
    private RelativeLayout mProgressbarLayout;
    /*右侧图标展示风格*/
    private @RightStyle
    int mRightStyle = RightStyle.mRightIconDefault;
    /*上方分割线展示风格*/
    private int mTopStyle = 0;
    /*底部分割线展示风格*/
    private int mBottomStyle = 0;
    /*是否展示点击反馈*/
    private boolean showClickFeedback = true;

    //===================对外API===================START
    private OnSettingItemClick onSettingItemClick;


    public SettingItem(Context context) {
        this(context, null);
    }

    public SettingItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        getCustomStyle(context, attrs);
        //获取到右侧展示风格，进行样式切换
        switchRightStyle(mRightStyle);

    }

    public TextView getLeftText() {
        return mTvLeftText;
    }

    public TextView getRightText() {
        return mTvRightText;
    }

    public ImageView getLeftIcon() {
        return mIvLeftIcon;
    }

    public ImageView getRightIcon() {
        return mIvRightIcon;
    }

    public void setRightCheckBoxDewable(@DrawableRes int drawableResId) {

        mRightIconCheck.setButtonDrawable(drawableResId);
    }
    //===================对外API===================END

    public SwitchCompat getRightIconSwitch() {
        if (mRightStyle == RightStyle.mRightIconSwitch) {
            return mRightIconSwitch;
        }
        return null;
    }

    public void setRightSwitchDewable(@DrawableRes int drawableResId) {
        mRightIconSwitch.setButtonDrawable(drawableResId);
    }

    public void setOnSettingItemClick(OnSettingItemClick onSettingItemClick) {
        this.onSettingItemClick = onSettingItemClick;
        mRootLayout.setOnClickListener(v -> {
            if (null != this.onSettingItemClick) {
                this.onSettingItemClick.onSettingItemClick(SettingItem.this);
            }
        });
    }

    public AppCompatCheckBox getRightIconCheckBox() {
        if (mRightStyle == RightStyle.mRightIconCheck) {
            return mRightIconCheck;
        }
        return null;
    }

    /**
     * 初始化自定义属性
     *
     * @param context
     * @param attrs
     */
    private void getCustomStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SettingView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.SettingView_leftText) {
                mTvLeftText.setText(a.getString(attr));
            } else if (attr == R.styleable.SettingView_rightText) {
                mTvRightText.setText(a.getString(attr));
            } else if (attr == R.styleable.SettingView_leftIcon) {
                // 左侧图标
                int resourceId = a.getResourceId(R.styleable.SettingView_leftIcon, 0);
                if (resourceId == 0) {
                    mIvLeftIcon.setVisibility(GONE);
                } else {
                    mIvLeftIcon.setVisibility(VISIBLE);
                    mIvLeftIcon.setImageResource(resourceId);
                }
            } else if (attr == R.styleable.SettingView_textSize) {
                // 默认设置为16sp，TypeValue也可以把sp转化为px
                mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                mTvLeftText.setTextSize(mTextSize);
            } else if (attr == R.styleable.SettingView_textColor) {
                //文字默认灰色
                mTextColor = a.getColor(attr, Color.LTGRAY);
                mTvLeftText.setTextColor(mTextColor);
            } else if (attr == R.styleable.SettingView_rightStyle) {
                mRightStyle = a.getInt(attr, RightStyle.mRightIconDefault);
            } else if (attr == R.styleable.SettingView_rightIcon) {
                int resourceId = a.getResourceId(R.styleable.SettingView_rightIcon, 0);
                if (resourceId == 0) {
                    mIvRightIcon.setVisibility(GONE);
                } else {
                    mIvRightIcon.setVisibility(VISIBLE);
                    mIvRightIcon.setImageResource(resourceId);
                }
            } else if (attr == R.styleable.SettingView_isShowUnderLine) {
                //默认不显示下面的分割线
                if (a.getBoolean(attr, false)) {
                    mUnderLine.setVisibility(View.VISIBLE);
                } else {
                    mUnderLine.setVisibility(View.GONE);
                }
            } else if (attr == R.styleable.SettingView_isShowTopLine) {
                //默认不显示上面的分割线
                if (a.getBoolean(attr, false)) {
                    mTopline.setVisibility(View.VISIBLE);
                } else {
                    mTopline.setVisibility(View.GONE);
                }
            } else if (attr == R.styleable.SettingView_topLineStyle) {
                mTopStyle = a.getInt(attr, 0);
                switchLineStyle(mTopStyle, mTopline);
            } else if (attr == R.styleable.SettingView_bottomLineStyle) {
                mBottomStyle = a.getInt(attr, 0);
                switchLineStyle(mBottomStyle, mUnderLine);
            } else if (attr == R.styleable.SettingView_showClickFeedback) {
                showClickFeedback = a.getBoolean(attr, true);
                if (showClickFeedback) {
                    TypedValue typedValue = new TypedValue();
                    getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
                    int[] attribute = new int[]{android.R.attr.selectableItemBackground};
                    TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
                    setBackground(typedArray.getDrawable(0));
                    setClickable(true);
                }
            }
        }
        a.recycle();
    }

    /**
     * 根据设定切换右侧展示样式，同时更新点击事件处理方式
     *
     * @param mRightStyle
     */
    public void switchRightStyle(@RightStyle int mRightStyle) {
        switch (mRightStyle) {
            case RightStyle.mRightIconDefault:
                //默认展示样式，只展示一个图标
                mIvRightIcon.setVisibility(View.VISIBLE);
                mRightIconCheck.setVisibility(View.GONE);
                mRightIconSwitch.setVisibility(View.GONE);
                mProgressbar.setVisibility(View.GONE);
                break;
            case RightStyle.mRightIconNone:
                //隐藏右侧图标
                mRightLayout.setVisibility(View.GONE);
                LayoutParams layoutParams = (LayoutParams) mTvRightText.getLayoutParams();
                layoutParams.removeRule(RelativeLayout.LEFT_OF);
                layoutParams.setMarginEnd(dip2px(getContext(), 8));
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mTvRightText.setLayoutParams(layoutParams);
                break;
            case RightStyle.mRightIconCheck:
                //显示选择框样式
                mIvRightIcon.setVisibility(View.GONE);
                mRightIconCheck.setVisibility(View.VISIBLE);
                mRightIconSwitch.setVisibility(View.GONE);
                mProgressbar.setVisibility(View.GONE);
                break;
            case RightStyle.mRightIconSwitch:
                //显示开关切换样式
                mIvRightIcon.setVisibility(View.GONE);
                mRightIconCheck.setVisibility(View.GONE);
                mRightIconSwitch.setVisibility(View.VISIBLE);
                mProgressbarLayout.setVisibility(GONE);
                mProgressbar.setVisibility(View.GONE);
                break;
            case RightStyle.mProgress:
                //显示开关切换样式
                mIvRightIcon.setVisibility(View.GONE);
                mRightIconCheck.setVisibility(View.GONE);
                mRightIconSwitch.setVisibility(View.GONE);
                mProgressbarLayout.setVisibility(VISIBLE);
                mProgressbar.setVisibility(View.VISIBLE);
                mProgressbar.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rotate_animation));
                break;
            default:
                break;
        }
        this.mRightStyle = mRightStyle;
    }

    /**
     * @param mLineStyle
     */
    private void switchLineStyle(int mLineStyle, View view) {
        LinearLayout.LayoutParams rlParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                dip2px(getContext(), 1));
        switch (mLineStyle) {
            case 0://center
                rlParam.setMargins(dip2px(getContext(), 16), 0, dip2px(getContext(), 16), 0);
                break;
            case 1://right
                rlParam.setMargins(dip2px(getContext(), 16), 0, 0, 0);
                break;
            case 2://left
                rlParam.setMargins(0, 0, dip2px(getContext(), 16), 0);
                break;
            case 3://full
                rlParam.setMargins(0, 0, 0, 0);
                break;
            default:
                rlParam.setMargins(dip2px(getContext(), 16), 0, dip2px(getContext(), 16), 0);
                break;
        }
        view.setLayoutParams(rlParam);

    }

    private void initView(Context context) {
        inflate = View.inflate(context, R.layout.settingitem, this);
        mRootLayout = inflate.findViewById(R.id.rootLayout);
        mTopline = inflate.findViewById(R.id.topline);
        mUnderLine = inflate.findViewById(R.id.underline);
        mTvLeftText = inflate.findViewById(R.id.tv_lefttext);
        mTvRightText = inflate.findViewById(R.id.tv_righttext);
        mIvLeftIcon = inflate.findViewById(R.id.iv_lefticon);
        mIvRightIcon = inflate.findViewById(R.id.iv_righticon);
        mRightLayout = inflate.findViewById(R.id.rightlayout);
        mRightIconCheck = inflate.findViewById(R.id.rightcheck);
        mRightIconSwitch = inflate.findViewById(R.id.rightswitch);
        mProgressbarLayout = inflate.findViewById(R.id.progressbar_layout);
        mProgressbar = inflate.findViewById(R.id.progressbar);
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @IntDef({
            RightStyle.mRightIconDefault,
            RightStyle.mRightIconNone,
            RightStyle.mRightIconCheck,
            RightStyle.mRightIconSwitch,
            RightStyle.mProgress,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface RightStyle {
        int mRightIconDefault = 0;
        int mRightIconNone = 1;
        int mRightIconCheck = 2;
        int mRightIconSwitch = 3;
        int mProgress = 4;
    }

    public interface OnSettingItemClick {
        void onSettingItemClick(View view);
    }

}