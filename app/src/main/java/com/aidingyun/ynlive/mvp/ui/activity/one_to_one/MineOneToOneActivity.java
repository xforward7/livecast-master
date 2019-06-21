package com.aidingyun.ynlive.mvp.ui.activity.one_to_one;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.mvp.ui.adapter.MyPagerAdapter;
import com.aidingyun.ynlive.mvp.ui.base.BaseActivity;
import com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment.CourseCollectListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment.CourseOpinionListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment.CourseOrganizationListFragment;
import com.aidingyun.ynlive.mvp.ui.widget.CustomViewPager;
import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MineOneToOneActivity extends BaseActivity {
    private XTabLayout mTabLayout;
    private CustomViewPager mViewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    {//构造代码块
        titles.add("待开始");
        titles.add("已完成");
        titles.add("已拒绝");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_one_to_one);
        initView();
        initViewPager();
    }
    private void initView() {
        mTabLayout = findViewById(R.id.xTablayout);
        mViewPager = findViewById(R.id.view_pager);
        ((ImageView)findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.tv_title)).setText("我的一对一");
    }

    private void initViewPager() {
        fragmentList.add(CourseCollectListFragment.newInstance());//课程
        fragmentList.add(CourseOpinionListFragment.newInstance());//观点
        fragmentList.add(CourseOrganizationListFragment.newInstance());//机构

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        mViewPager.setOffscreenPageLimit(titles.size());
        mViewPager.setAdapter(adapter);
        mViewPager.setScanScroll(false);//设置不可左右滑动
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
//        mTabLayout.getTabAt(0).select();
        // 这样可以自定义tab的布局与内容了

    }
}
