package com.aidingyun.ynlive.mvp.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.inter.IndexRefresh;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.di.component.DaggerHomePageComponent;
import com.aidingyun.ynlive.di.module.HomePageModule;
import com.aidingyun.ynlive.mvp.contract.HomePageContract;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.aidingyun.ynlive.mvp.presenter.HomePagePresenter;
import com.aidingyun.ynlive.mvp.ui.activity.Message.MessageActivity;
import com.aidingyun.ynlive.mvp.ui.activity.account.LoginActivity;
import com.aidingyun.ynlive.mvp.ui.activity.search.SearchCourseActivity;
import com.aidingyun.ynlive.mvp.ui.adapter.MainPageAdapter;
import com.aidingyun.ynlive.mvp.ui.widget.BadgeButton;
import com.androidkun.xtablayout.XTabLayout;
import com.facebook.stetho.common.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomePageFragment extends BaseFragment<HomePagePresenter> implements HomePageContract.View, IndexRefresh, View.OnClickListener {


    List<BaseFragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    private View view;
    /**
     * 24日18:00
     */
    private TextView mTvTimer;
    private ImageView mSearch;
    private BadgeButton mUserNotification;
    private XTabLayout mTabLayout;
    private ViewPager mViewPager;

    {//构造代码块
        titles.add("推荐");
        if (ABaseService.courseClassificationModel!=null){
            for (CourseClassificationModel.DataBean databean :ABaseService.courseClassificationModel.getData()) {
                titles.add(databean.getType_name());
            }
        }
    }

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomePageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homePageModule(new HomePageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        mSearch = view.findViewById(R.id.search);
        mSearch.setOnClickListener(this);
        mUserNotification = view.findViewById(R.id.user_notification);
        mUserNotification.setOnClickListener(this);
        mTabLayout = view.findViewById(R.id.xTablayout);
        mViewPager = view.findViewById(R.id.view_pager);
        return view;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initViewPager();
    }

    private void initViewPager() {
//        fragmentList.add(HomePageListFragment.newInstance());//推荐
//         fragmentList.add(FinanceFragment.newInstance());//财经
//        fragmentList.add(EducationFragment.newInstance());//教育
//        fragmentList.add(LegalFragment.newInstance());//法律
//        fragmentList.add(OtherFragment.newInstance());//其他\


        for (int i = 0; i < titles.size(); i++) {
            if (i==0){
                fragmentList.add(HomePageListFragment.newInstance(0,""));
            }else {
                LogUtil.e("index=========================="+i);
                for (int j = 1; j < ABaseService.courseClassificationModel.getData().size(); j++) {
                    fragmentList.add(HomePageListFragment.newInstance(j,ABaseService.courseClassificationModel.getData().get(j).getTypeid()));
                }
            }
        }
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        MainPageAdapter adapter = new MainPageAdapter(getChildFragmentManager(), fragmentList, titles);
        mViewPager.setOffscreenPageLimit(titles.size());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void doUIRefresh() {

    }

    @Override
    public Fragment getFragment() {
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.layout_relat:
                // 课程待开始提示

                break;
            case R.id.search:
                // 课程搜索
                SearchCourseActivity.start(getActivity(),"");
                break;
            case R.id.user_notification:
                // 我的消息
                if (ABaseService.islogin) {
                    startActivity(new Intent(getActivity(), MessageActivity.class));
                } else  {
                    startActivityForResult(new Intent(getActivity(),LoginActivity.class),Activity.RESULT_OK);
                }
                break;

        }
    }



}
