package com.aidingyun.ynlive.mvp.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aidingyun.ynlive.component.log.LogUtils;
import com.aidingyun.ynlive.mvp.model.annotation.MainPageId;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerNavigation extends LinearLayout {

    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private List<NavigateItem> mNavItemList;
    private NavigateItem mCurrNavigateItem;

    private TabClickListener mClickListener = new TabClickListener();

    private OnNavItemListener mOnNavItemListener;

    public ViewPagerNavigation(Context context) {
        super(context);
    }

    public ViewPagerNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    public void notifyDataChanged() {
        if (mViewPagerAdapter != null) {
            mViewPagerAdapter.notifyDataSetChanged();
        }
    }

    public void setCurrPageIndex(int index) {
        NavigateItem newItem = null;
        for (int i = 0; i < mNavItemList.size(); i++) {
            if (mNavItemList.get(i).mActivityId == index) {
                newItem = mNavItemList.get(i);
                break;
            }
        }

        if (newItem != mCurrNavigateItem && mOnNavItemListener != null && mCurrNavigateItem != null) {
            mOnNavItemListener.onItemIndexChanged(index);
        }

        if (mCurrNavigateItem != null) {
            mCurrNavigateItem.setFocus(false, getContext());
        }
        newItem.setFocus(true, getContext());
        mCurrNavigateItem = newItem;
    }

    public void setNavigateItems(List<NavigateItem> items, List<View> views) {

        if (views != null && views.size() != 0) {
            mViewPagerAdapter = new ViewPagerAdapter(views);
            mViewPager.setAdapter(mViewPagerAdapter);
            mNavItemList = items;
        }

        for (int i = 0; i < mNavItemList.size(); i++) {
            NavigateItem item = mNavItemList.get(i);

            View child = item.getItemView();
            child.setTag(item.mActivityId);
            child.setOnClickListener(mClickListener);
            this.addView(item.getItemView());
        }
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void setOnNavItemListener(OnNavItemListener listener) {
        mOnNavItemListener = listener;
    }

    public interface OnNavItemListener {
        /**
         * 只有改变才会触发
         */
        void onItemIndexChanged(int index);

        /**
         * 点击必定触发
         */
        void onItemClicked(int index);
    }

    private class TabClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            int id = (Integer) view.getTag();
            if (id != MainPageId.INDEX_ACTIVITY) {
                int oldIndex = mViewPager.getCurrentItem();
                if (oldIndex != id) {
                    try {
//                        Fragment oldFragment = mViewPagerAdapter.getItem(oldIndex);
//                        Fragment newFragment = mViewPagerAdapter.getItem(id);
//                        oldFragment.onPause();
//                        newFragment.onResume();
                    } catch (Exception e) {
                        LogUtils.e("ViewPager", "", e);
                    }
                }
                if (mViewPager != null) {
                    mViewPager.setCurrentItem(id);
                }
                setCurrPageIndex(id);//1.触发index changed 事件 2.点击切换与否的图片
            }
            if (mOnNavItemListener != null) {
                mOnNavItemListener.onItemClicked(id);//1.触发 item clicked 事件
            }
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {

        private List<View> mPages;

        public ViewPagerAdapter(List<View> pages) {
            this.mPages = pages;
        }

        @Override
        public int getCount() {
            return mPages == null ? 0 : mPages.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mPages.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mPages.get(position), 0);
            return mPages.get(position);
        }

    }

}
