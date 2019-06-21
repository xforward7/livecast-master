package com.aidingyun.ynlive.mvp.model.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     project name: Ruisi
 *     author      : 翁嘉若
 *     create time : 2017/10/21 下午2:38
 *     desc        : 描述--//Sex
 * </pre>
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({NumType.INCREASE, NumType.DECREASE})
public @interface NumType {
    int INCREASE = 1;//增加
    int DECREASE = -1;//减少
}
