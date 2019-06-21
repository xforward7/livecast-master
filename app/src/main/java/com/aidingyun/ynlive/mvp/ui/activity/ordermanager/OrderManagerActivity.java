package com.aidingyun.ynlive.mvp.ui.activity.ordermanager;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;
import com.aidingyun.ynlive.mvp.ui.adapter.MyPagerAdapter;
import com.aidingyun.ynlive.mvp.ui.fragment.course_detail.CourseCommentListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.course_detail.CourseListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.course_detail.CourseReferralFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.order_fragment.HasPaymentFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.order_fragment.NoPaymentFragment;
import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class OrderManagerActivity extends AppCompatActivity {

    private XTabLayout mTabLayout;
    private ViewPager mViewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    TextView tv_title;
    ImageView iv_back;
    private UpdateVersionUtils updateVersionUtils = null;
    {//构造代码块
        titles.add("已支付");
        titles.add("待支付");
    }


    public static void start(Context context) {
        Router.newIntent(context)
//                .putString("courseid",courseid)
                .to(OrderManagerActivity.class)
                .launch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manager);
        mTabLayout = findViewById(R.id.xTablayout);
        mViewPager = findViewById(R.id.view_pager);
        ((ImageView)findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.tv_title)).setText("我的订单");
        updateVersionUtils = new UpdateVersionUtils();
        initViewPager();
    }


    private void initViewPager() {
        fragmentList.add(HasPaymentFragment.newInstance());//
        fragmentList.add(NoPaymentFragment.newInstance());//

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        mViewPager.setOffscreenPageLimit(titles.size());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器

    }



}
