package com.aidingyun.ynlive.mvp.ui.widget;

import android.support.annotation.IntDef;
import android.support.design.widget.AppBarLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
if (state == State.EXPANDED) {//展开状态

} else if (state == State.COLLAPSED) {//折叠状态

} else {//中间状态

}
*/
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({State.EXPANDED,State.COLLAPSED,State.IDLE})
    public @interface State {
       int EXPANDED = 0;
       int COLLAPSED =1;
       int IDLE=2;
    }
 
    private @State int mCurrentState = State.IDLE;
 
    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (offset == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED,offset);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(offset) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED, offset);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE, offset);
            }
            mCurrentState = State.IDLE;
        }
    }
 
    public abstract void onStateChanged(AppBarLayout appBarLayout, int state, int offset);
}