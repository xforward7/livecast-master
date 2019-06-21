package com.aidingyun.ynlive.mvp.ui.widget;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.aidingyun.core.cache.SharedPref;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.CustomCountDownTimer;


/**
 * author: zheng
 * created on: 2017/3/2 17:58
 * description:获取验证码按钮
 * version: 1.0
 */

public class VerifyCodeButton extends android.support.v7.widget.AppCompatButton {

    private String key;
    private static final long ONE_MINUTE = 60_000;
    private long lastGetVerifiCodeTime;
    private CustomCountDownTimer timer;
    private int textColorSecond = ContextCompat.getColor(getContext(), R.color.colorAccent);//字体颜色->倒计时
    private int textColorReGet = ContextCompat.getColor(getContext(), R.color.colorWhite);//字体颜色->重新获取

    public VerifyCodeButton(Context context) {
        this(context, null);
    }

    public VerifyCodeButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyCodeButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 当调用此方法时,该控件会初始化状态
     */
    public void setKeyAndInitState(String key) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("invalid key");
        }
        this.key = key;

        //从本地获取上次获取验证码时间
        lastGetVerifiCodeTime = SharedPref.getInstance(getContext()).getLong(key, 0L);
        //针对还没有倒计时完成就退出了该页面的情况
        long duration = SystemClock.elapsedRealtime() - lastGetVerifiCodeTime;
        long surplus = ONE_MINUTE - duration;
        if (lastGetVerifiCodeTime != 0 && surplus > 0 && surplus < ONE_MINUTE) {
            setEnabled(false);
            createTimer(surplus);
        } else {
            lastGetVerifiCodeTime = 0;
        }
    }

    /**
     * 当输入条件正确时,调用该方法,记录最后一次获取验证码的时间
     *
     * @param key 用于区分获取验证码的情况的key
     */
    public void recordLastGetVerifiCodeTime(String key) {
        setEnabled(false);
        lastGetVerifiCodeTime = SystemClock.elapsedRealtime();
        SharedPref.getInstance(getContext()).putLong(key, lastGetVerifiCodeTime);
        createTimer(ONE_MINUTE);
    }

    /**
     * 创建 一个计时器
     *
     * @param countdownTime 倒数总毫秒数
     */
    private void createTimer(long countdownTime) {
        timer = new CustomCountDownTimer(countdownTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setText(TextUtils.concat(String.valueOf(millisUntilFinished / 1000), "s"));
                setTextColor(textColorSecond);
            }

            @Override
            public void onFinish() {
                setEnabled(true);
                setText("重新获取");
                setTextColor(textColorReGet);
                lastGetVerifiCodeTime = 0;
                timer = null;
            }
        };
        timer.start();
    }

    public void setTextColorSecond(@ColorRes int colorRes) {
        this.textColorSecond = ContextCompat.getColor(getContext(), colorRes);
    }

    public void setTextColorReGet(@ColorRes int colorRes) {
        this.textColorReGet = ContextCompat.getColor(getContext(), colorRes);
    }

    /**
     * 销毁计时控件
     */
    public void onDestroy() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }
}
