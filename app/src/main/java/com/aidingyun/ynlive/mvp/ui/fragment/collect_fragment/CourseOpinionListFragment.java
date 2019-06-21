package com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment;

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

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CollectCourseModel;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.ui.adapter.collect.CollectRecycleItemAdapter;
import com.google.gson.Gson;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;


/**
 * 个人收藏——观点
 */
public class CourseOpinionListFragment extends Fragment {
    private RelativeLayout network_fail;

    RecyclerView recycler_view;

    private UpdateVersionUtils updateVersionUtils = null;

    CollectRecycleItemAdapter collectRecycleItemAdapter;

    List<CourseDetailInfo.SectionBean> sectionBeans = new ArrayList<>();

    public static CourseOpinionListFragment newInstance() {
        Bundle args = new Bundle();
//        args.putString("hostid", hostid);
        CourseOpinionListFragment fragment = new CourseOpinionListFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_fragment_list_item, container, false);
        updateVersionUtils = new UpdateVersionUtils();
        recycler_view = view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new GridLayoutManager(recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));
//        collectRecycleItemAdapter = new CollectRecycleItemAdapter(getActivity(),sectionBeans);
//        recycler_view.setAdapter(collectRecycleItemAdapter);
//        network_fail = view.findViewById(R.id.network_fail);//网络连接失败布局
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); //获取参数
        Bundle arguments = getArguments(); //改变值
//        mTv.setText(arguments.getString("tag"));
        searchCourseComment();
    }


    @SuppressLint("LongLogTag")
    private void searchCourseComment() {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("op", "view");
        updateVersionUtils.postByName(Global.GET_COLLECT_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("CourseOpinionListFragment","result++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        Gson gson = new Gson();
                        CollectCourseModel collectCourseModel = gson.fromJson(result,CollectCourseModel.class);

                    }else{
                        ToastUtils.showError(getActivity(),jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {

            }
        });

    }

}
