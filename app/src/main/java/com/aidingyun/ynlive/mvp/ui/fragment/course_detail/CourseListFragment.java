package com.aidingyun.ynlive.mvp.ui.fragment.course_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemAdapterCourseList;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemAdapterTypeCourse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;


public class CourseListFragment extends Fragment {
    private int mIndex;
    private String type_id="";
    RecyclerView rlChannels;
    SmartRefreshLayout refreshLayout;
    private RelativeLayout network_fail;
    private RecycleAdapter adapter;
    private int offset = 0;

    private UpdateVersionUtils updateVersionUtils = null;
    RecyclerView recycler_view;

    RelativeLayout Live_lecture_series;
    TextView tv_total_course;
    TextView tv_many_course;
    RecycleItemAdapterCourseList courseAdapter;

    List<CourseDetailInfo.SectionBean> sectionBeans = new ArrayList<>();

    public static CourseListFragment newInstance(String isLoad) {
        Bundle args = new Bundle();
        args.putString("isLoad", isLoad);
        CourseListFragment fragment = new CourseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_fragment_list_item, container, false);
        updateVersionUtils = new UpdateVersionUtils();
        recycler_view = view.findViewById(R.id.recycler_view);
        Live_lecture_series = view.findViewById(R.id.Live_lecture_series);
        tv_total_course = view.findViewById(R.id.tv_total_course);
        tv_many_course = view.findViewById(R.id.tv_many_course);
        recycler_view.setLayoutManager(new GridLayoutManager(recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));
        if (ABaseService.courseDetailInfo!=null){
            sectionBeans = ABaseService.courseDetailInfo.getSection();
        }
        Live_lecture_series.setVisibility(ABaseService.courseDetailInfo.getData().getType().equals("0") ? View.VISIBLE:View.GONE);
        courseAdapter = new RecycleItemAdapterCourseList(getActivity(),sectionBeans);
        recycler_view.setAdapter(courseAdapter);
//        network_fail = view.findViewById(R.id.network_fail);//网络连接失败布局
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); //获取参数
        Bundle arguments = getArguments(); //改变值
//        mTv.setText(arguments.getString("tag"));
//        searchCourseDetail(arguments.getString("courseid"));
    }

}
