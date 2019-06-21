package com.aidingyun.ynlive.mvp.ui.fragment.course_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleAdapter;
import com.aidingyun.ynlive.mvp.ui.widget.CircleImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;



public class CourseReferralFragment extends Fragment {
    private int mIndex;
    private String type_id="";
    RecyclerView rlChannels;
    SmartRefreshLayout refreshLayout;
    private RelativeLayout network_fail;
    private RecycleAdapter adapter;
    private int offset = 0;

    TextView tv_title;
    TextView tv_start;
    TextView tv_collent;
    TextView tv_price;
    TextView tv_unit;
    CircleImageView circle_head;
    TextView tv_teach_name;
    TextView tv_technical;
    TextView tv_teach_start;
    TextView tv_context;

    private UpdateVersionUtils updateVersionUtils = null;

    public static CourseReferralFragment newInstance(String isLoad) {
        Bundle args = new Bundle();
        args.putString("isLoad", isLoad);
        CourseReferralFragment fragment = new CourseReferralFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_referral_fragment_item, container, false);
        updateVersionUtils = new UpdateVersionUtils();
        tv_title = view.findViewById(R.id.tv_title);
        tv_start = view.findViewById(R.id.tv_start);
        tv_collent = view.findViewById(R.id.tv_collent);
        tv_price = view.findViewById(R.id.tv_price);
        tv_unit = view.findViewById(R.id.tv_unit);
        circle_head = view.findViewById(R.id.circle_head);
        tv_teach_name = view.findViewById(R.id.tv_teach_name);
        tv_technical = view.findViewById(R.id.tv_technical);
        tv_teach_start = view.findViewById(R.id.tv_teach_start);
        tv_context = view.findViewById(R.id.tv_context);
//        network_fail = view.findViewById(R.id.network_fail);//网络连接失败布局
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); //获取参数
        Bundle arguments = getArguments(); //改变值
//        mTv.setText(arguments.getString("tag"));
        if (arguments.getString("isLoad").equals("true")){
                tv_title.setText(ABaseService.courseDetailInfo.getData().getTitle());
                tv_start.setText(ABaseService.courseDetailInfo.getData().getScore());
                tv_collent.setText(ABaseService.courseDetailInfo.getData().getCollection() + "人在学");
            if (ABaseService.courseDetailInfo.getData().getAllow().equals("0")) {
                tv_price.setText("免费");
                tv_unit.setVisibility(View.GONE);
            } else if (ABaseService.courseDetailInfo.getData().getAllow().equals("1")) {
                tv_price.setText("加密");
                tv_unit.setVisibility(View.GONE);
            } else if (ABaseService.courseDetailInfo.getData().getAllow().equals("2")) {
                tv_price.setText(ABaseService.courseDetailInfo.getData().getPrice());
            }
            if (ABaseService.courseDetailInfo.getData().getCourse_type()==0) {
                LoadImage.loadCircleImage(getActivity(), ABaseService.courseDetailInfo.getData().getPhoto(), circle_head);
                tv_teach_name.setText(ABaseService.courseDetailInfo.getHost_data().getReal_name());
                tv_technical.setText(ABaseService.courseDetailInfo.getHost_data().getProfessional());
                tv_teach_start.setText(ABaseService.courseDetailInfo.getHost_data().getScore());
                tv_context.setText(ABaseService.courseDetailInfo.getHost_data().getIntro());
            }else {
                LoadImage.loadCircleImage(getActivity(), ABaseService.courseDetailInfo.getOrganization_data().getOrganization_icon(), circle_head);
                tv_teach_name.setText(ABaseService.courseDetailInfo.getOrganization_data().getName());
                tv_teach_start.setText(ABaseService.courseDetailInfo.getOrganization_data().getScore());
                tv_context.setText(ABaseService.courseDetailInfo.getOrganization_data().getOrganization_summary());
            }
        }

    }

}
