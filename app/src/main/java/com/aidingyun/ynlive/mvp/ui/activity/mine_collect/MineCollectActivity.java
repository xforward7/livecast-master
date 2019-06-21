package com.aidingyun.ynlive.mvp.ui.activity.mine_collect;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.ui.adapter.MyPagerAdapter;
import com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment.CourseCollectListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment.CourseOpinionListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment.CourseOrganizationListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment.CoursePKListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment.OpinionCollectionWXFragment;
import com.aidingyun.ynlive.mvp.ui.widget.CustomViewPager;
import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MineCollectActivity extends AppCompatActivity
{
    private XTabLayout mTabLayout;
    private CustomViewPager mViewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    private UpdateVersionUtils updateVersionUtils = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collect);
        initView();
    }


    private void initView() {
        ((ImageView)findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.tv_title)).setText("我的收藏");
        updateVersionUtils = new UpdateVersionUtils();
        mTabLayout = findViewById(R.id.xTablayout);
        mViewPager = findViewById(R.id.view_pager);

        initViewPager();
    }


    {//构造代码块
        titles.add("课程");
        titles.add("直播间");
        titles.add("机构");
        titles.add("观点");
    }

    private void initViewPager() {
        fragmentList.add(CourseCollectListFragment.newInstance());//课程
        fragmentList.add(CoursePKListFragment.newInstance());//直播间
        fragmentList.add(CourseOrganizationListFragment.newInstance());//机构
        fragmentList.add(CourseOpinionListFragment.newInstance());//观点
//        fragmentList.add(OpinionCollectionWXFragment.newInstance());//观点

//        for (int j = 1; j < ABaseService.courseClassificationModel.getData().size(); j++) {
//            fragmentList.add(HomePageListFragment.newInstance(j,ABaseService.courseClassificationModel.getData().get(j).getTypeid()));
//        }
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


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
