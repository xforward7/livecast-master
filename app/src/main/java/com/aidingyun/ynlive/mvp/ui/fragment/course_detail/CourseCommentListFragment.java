package com.aidingyun.ynlive.mvp.ui.fragment.course_detail;

import android.annotation.SuppressLint;
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
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemAdapterCourseList;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemCourseCommentItemAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;


public class CourseCommentListFragment extends Fragment {
    private RelativeLayout network_fail;

    RecyclerView recycler_view;
    TextView tv_score;

    private UpdateVersionUtils updateVersionUtils = null;

    RecycleItemCourseCommentItemAdapter commentItemAdapter;

    List<CourseDetailInfo.SectionBean> sectionBeans = new ArrayList<>();

    public static CourseCommentListFragment newInstance(String type,String typeid,String hostid) {
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("typeid", typeid);
        args.putString("hostid", hostid);
        CourseCommentListFragment fragment = new CourseCommentListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_comment_layout, container, false);
        updateVersionUtils = new UpdateVersionUtils();
        tv_score = view.findViewById(R.id.tv_score);
        recycler_view = view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new GridLayoutManager(recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));
        if (ABaseService.courseDetailInfo!=null){
            sectionBeans = ABaseService.courseDetailInfo.getSection();
        }
        tv_score.setText(ABaseService.courseDetailInfo.getData().getScore());
        commentItemAdapter = new RecycleItemCourseCommentItemAdapter(getActivity(),sectionBeans);
        recycler_view.setAdapter(commentItemAdapter);
//        network_fail = view.findViewById(R.id.network_fail);//网络连接失败布局
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); //获取参数
        Bundle arguments = getArguments(); //改变值
//        mTv.setText(arguments.getString("tag"));
//        searchCourseComment(arguments.getString("type"),arguments.getString("typeid"),arguments.getString("hostid"));
        searchCourseComment(ABaseService.courseDetailInfo.getData().getTypeid());
    }


    @SuppressLint("LongLogTag")
    private void searchCourseComment(String typeid) {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("typeid", typeid);
        updateVersionUtils.postByName(Global.GET_COURSE_COMMENT_LIST_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("CourseCommentListFragment","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        Gson gson = new Gson();
//                        ABaseService.courseDetailInfo = gson.fromJson(result,CourseDetailInfo.class);
//                        rlChannels.setLayoutManager(new GridLayoutManager(rlChannels.getContext(), 2, GridLayoutManager.VERTICAL, false));
//                        adapter = new RecycleAdapter(getActivity(),ABaseService.homeCourseModel);
//                        rlChannels.setAdapter(adapter);
                    }else{
//                        ToastUtils.showError(getActivity(),jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("SearchCourseActivity","updateVersionUtils++++++++++++++++"+error);
            }
        });

    }

}
