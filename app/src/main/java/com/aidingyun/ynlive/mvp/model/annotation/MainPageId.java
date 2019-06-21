package com.aidingyun.ynlive.mvp.model.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     project name: Ruisi
 *     author      : 李琼
 *     create time : 2017/10/21 下午2:38
 *     desc        : 描述--//Sex
 *     Reference   :
 *     modifier               :
 *     modification time      :
 *     modify remarks         :
 *     @version: --//1.0
 * </pre>
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        MainPageId.INDEX_ACTIVITY,
        MainPageId.HomePage,
        MainPageId.Sort,
        MainPageId.Expert,
        MainPageId.Standpoint,
        MainPageId.User}
)
public @interface MainPageId {
    int INDEX_ACTIVITY = -1001;//点击事件对应的是跳转Activity 值要小于0
    int HomePage = 0;//MainActivity的page  >0 按照顺序
    int Sort = 1;
    int Expert = 2;
    int Standpoint = 3;
    int User = 4;
}
