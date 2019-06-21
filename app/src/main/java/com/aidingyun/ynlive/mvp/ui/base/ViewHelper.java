package com.aidingyun.ynlive.mvp.ui.base;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * <pre>
 *     project name: JMDroid
 *     author      : 翁嘉若
 *     create time : 2018/8/2 下午5:33
 *     desc        : 描述--//ViewHelper
 * </pre>
 */

public class ViewHelper {

    private Activity mActivity;

    public ViewHelper(Activity mActivity) {
        this.mActivity = mActivity;
    }

    protected RecyclerView initRecyclerView(RecyclerView.Adapter adapter) {
        return initRecyclerView(new LinearLayoutManager(mActivity), adapter);
    }

    protected RecyclerView initRecyclerView(RecyclerView.LayoutManager mLayoutManager, RecyclerView.Adapter adapter) {
        return initRecyclerView(mLayoutManager, adapter, R.id.recycler_view);
    }

    protected RecyclerView initRecyclerView(RecyclerView.LayoutManager mLayoutManager,
                                            RecyclerView.Adapter adapter,
                                            @IdRes int idRes) {
        RecyclerView mRecyclerView = mActivity.findViewById(idRes);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//干掉操蛋的刷新闪烁问题
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return mRecyclerView;
    }

    protected SmartRefreshLayout initRefreshLayout(OnRefreshLoadMoreListener listener) {
        SmartRefreshLayout mRefreshLayout = mActivity.findViewById(R.id.refreshLayout);
        if (mRefreshLayout != null) {
            mRefreshLayout.setOnRefreshLoadMoreListener(listener);
        }
        return mRefreshLayout;
    }

    protected View initEmptyView(BaseQuickAdapter adapter,
                                 RecyclerView mRecyclerView) {
        return initEmptyView(adapter, mRecyclerView, "暂无数据");
    }

    protected View initEmptyView(BaseQuickAdapter adapter,
                                 RecyclerView mRecyclerView,
                                 String strHint) {
        return initEmptyView(adapter, mRecyclerView, strHint, R.drawable.ic_empty_data_book);
    }

    protected View initEmptyView(BaseQuickAdapter adapter,
                                 RecyclerView mRecyclerView,
                                 String strHint,
                                 @DrawableRes int resId) {
        return initEmptyView(adapter, mRecyclerView, strHint, resId, R.layout.default_empty_view);
    }

    protected View initEmptyView(BaseQuickAdapter adapter,
                                 RecyclerView mRecyclerView,
                                 String strHint,
                                 @DrawableRes int resId,
                                 @LayoutRes int layoutRes) {
        View emptyRecView = LayoutInflater.from(mActivity).inflate(layoutRes, (ViewGroup) mRecyclerView.getParent(), false);
        ImageView emptyRecImg = emptyRecView.findViewById(R.id.empty_img);
        emptyRecImg.setImageResource(resId);
        TextView emptyRecString = emptyRecView.findViewById(R.id.empty_string);
        emptyRecString.setText(strHint);
        adapter.setEmptyView(emptyRecView);
        return emptyRecView;
    }

}
