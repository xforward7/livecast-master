package com.aidingyun.ynlive.mvp.ui.activity.organization;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

public class OrganizationDetailActivity extends BaseActivity {
    private XTabLayout mTabLayout;
    private ViewPager mViewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    {//构造代码块
        titles.add("课程列表");
        titles.add("机构老师");
        titles.add("关于我们");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_detail);
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
        ((TextView)findViewById(R.id.tv_title)).setText("我的问答");
        ((ImageView)findViewById(R.id.iv_download)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.iv_share)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 分享
                 */
            }
        });
    }

    private void initViewPager() {
        fragmentList.add(CourseCollectListFragment.newInstance());//课程
        fragmentList.add(CourseOpinionListFragment.newInstance());//观点
        fragmentList.add(CourseOrganizationListFragment.newInstance());//机构

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        mViewPager.setOffscreenPageLimit(titles.size());
        mViewPager.setAdapter(adapter);
//        mViewPager.setScanScroll(false);//设置不可左右滑动
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
//        mTabLayout.getTabAt(0).select();

    }

}
