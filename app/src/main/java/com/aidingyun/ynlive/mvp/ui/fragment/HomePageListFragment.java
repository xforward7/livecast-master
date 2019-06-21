package com.aidingyun.ynlive.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.di.component.DaggerRecommendComponent;
import com.aidingyun.ynlive.di.module.RecommendModule;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.contract.RecommendContract;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.presenter.RecommendPresenter;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleAdapter;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vector.update_app.HttpManager;


import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomePageListFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View {
    private int mIndex;
    private String type_id = "";
    RecyclerView rlChannels;
    SmartRefreshLayout refreshLayout;
    private RelativeLayout network_fail;
    private RecycleAdapter adapter;

    private UpdateVersionUtils updateVersionUtils = null;

    public static HomePageListFragment newInstance(int index, String type_id) {
        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putString("type_id", type_id);
        HomePageListFragment fragment = new HomePageListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerRecommendComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.default_rv_refresh_loadmore, container, false);
        updateVersionUtils = new UpdateVersionUtils();
        rlChannels = view.findViewById(R.id.recycler_view);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        network_fail = view.findViewById(R.id.network_fail);
        adapter = new RecycleAdapter(getActivity(),ABaseService.homeCourseModel);

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (mIndex == 0){
                    type_id = "";
                    searchCourseList(type_id);
                } else {
                    type_id = getArguments().getString("type_id", "");
                    searchCourseList(type_id);
                }
                adapter.notifyDataSetChanged();
            }
        });

        refreshLayout.setEnableLoadMore(false);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.douban.com/v2/")
//                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
//                .build();

        return view;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mIndex = getArguments().getInt("index", 0);
        if (mIndex==0){
            type_id = "";
            searchCourseList(type_id);
        } else {
            type_id = getArguments().getString("type_id", "");
            searchCourseList(type_id);
        }
    }



    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                List<CurriculumTypeModel> results = adapter.getResults();
//                results.add(0, new CurriculumTypeModel("", "new fresh item"));
//                results.add(0, new CurriculumTypeModel("", "new fresh item"));
//                adapter.notifyDataSetChanged();
//            }
//        });

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


    private void searchCourseList(String typeid) {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("typeid", typeid);
        updateVersionUtils.postByName(Global.GET_INDEX_LIST_SERVICE_NAME, reqBody, new HttpManager.Callback() {

            @Override
            public void onResponse(String result) {

                if (refreshLayout.getState() == RefreshState.Refreshing) {
                    refreshLayout.finishRefresh();
                }

                Log.e("SearchCourseActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        Gson gson = new Gson();
                        ABaseService.homeCourseModel = gson.fromJson(result,HomeCourseModel.class);
                        rlChannels.setLayoutManager(new GridLayoutManager(rlChannels.getContext(), 2, GridLayoutManager.VERTICAL, false));
                        adapter = new RecycleAdapter(getActivity(),ABaseService.homeCourseModel);
                        rlChannels.setAdapter(adapter);
                    }else{
                        ToastUtils.showError(getActivity(),jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                if (refreshLayout.getState() == RefreshState.Refreshing) {
                    refreshLayout.finishRefresh();
                }
                Log.e("SearchCourseActivity","updateVersionUtils++++++++++++++++"+error);
            }
        });

    }

}
