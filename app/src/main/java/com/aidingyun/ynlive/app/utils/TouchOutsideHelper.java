package com.aidingyun.ynlive.app.utils;

import android.support.annotation.IdRes;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * <pre>
 *     project name: xiaotianbing_android
 *     author      : 翁嘉若
 *     create time : 2018/7/27 下午4:38
 *     desc        : 描述--//TouchOutsideHelper 点击非输入框处关闭键盘
 * </pre>
 */

public class TouchOutsideHelper {

    /**
     * 清除editText的焦点
     *
     * @param v   焦点所在View
     * @param ids 输入框
     */
    public void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }
    }

    /**
     * 隐藏键盘
     *
     * @param v   焦点所在View
     * @param ids 输入框
     * @return true代表焦点在edit上
     */
    public boolean isFocusEditText(View v, int... ids) {
        if (v instanceof EditText) {
            EditText tmp_et = (EditText) v;
            for (int id : ids) {
                if (tmp_et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param views 传入要过滤的View,过滤之后点击将不会有隐藏软键盘的操作
     *              是否触摸在指定view上面,对某个控件过滤
     */
    public boolean isTouchView(View[] views, MotionEvent ev) {
        if (views == null || views.length == 0) {
            return false;
        }
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 传入EditText的Id
     * 没有传入的EditText不做处理
     *
     * @return id 数组
     */
    public int[] hideSoftByEditViewIds(@IdRes int... ids) {
        return ids;
    }
}
