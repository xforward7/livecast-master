package com.aidingyun.ynlive.mvp.ui.base;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.aidingyun.core.utils.Kits;
import com.aidingyun.ynlive.component.app.GlobalContext;


/**
 * author: zheng
 * created on: 2017/2/27 10:18
 * description:
 * version: 1.0
 */
public abstract class IGestureActivity extends IBaseActivity {

    public int pxIntVertical = Kits.Dimens.dpToPxInt(GlobalContext.getAppContext(), 220);//dp转px

    public void setTouchVertical(int dimenInt, @Kits.Dimens.DimensUnit int unitDimen) {
        this.pxIntVertical = unitDimen == Kits.Dimens.UNIT_DP ? Kits.Dimens.dpToPxInt(GlobalContext.getAppContext(), dimenInt) : dimenInt;
    }

    public int pxIntHorizontal = Kits.Dimens.dpToPxInt(GlobalContext.getAppContext(), 200);//dp转px

    public void setTouchHorizontal(int dimenInt, @Kits.Dimens.DimensUnit int unitDimen) {
        this.pxIntHorizontal = unitDimen == Kits.Dimens.UNIT_DP ? Kits.Dimens.dpToPxInt(GlobalContext.getAppContext(), dimenInt) : dimenInt;
    }

    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e2.getX() - e1.getX();
            float deltaY = e2.getY() - e1.getY();
            //判定为上下滑动
            if (Math.abs(deltaY) > Math.abs(deltaX) && Math.abs(deltaY) > pxIntVertical) {
                return deltaY < 0 ? onFlingUp() : onFlingDown();
            }
            //判定为左右滑动
            if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > pxIntHorizontal) {
                return deltaX < 0 ? onFlingLeft() : onFlingRight();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

    private GestureDetector gestureDetector;

    protected boolean onFlingDown() {
        return false;
    }

    protected boolean onFlingUp() {
        return false;
    }

    protected boolean onFlingRight() {
        return false;
    }

    protected boolean onFlingLeft() {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (gestureDetector == null) {
            gestureDetector = new GestureDetector(this, gestureListener);
        }
        return gestureDetector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

}