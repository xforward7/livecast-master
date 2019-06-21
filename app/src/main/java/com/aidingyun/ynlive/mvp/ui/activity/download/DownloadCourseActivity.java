package com.aidingyun.ynlive.mvp.ui.activity.download;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.aidingyun.ynlive.mvp.ui.adapter.download.RecycleItemDownloadCourseAdapter;
import com.aidingyun.ynlive.mvp.ui.base.BaseActivity;
import com.google.gson.Gson;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class DownloadCourseActivity extends BaseActivity {
    RecyclerView recycler_view;
    RecycleItemDownloadCourseAdapter downloadCourseAdapter;
    List<CourseDetailInfo.SectionBean> sectionBeans;
    CheckBox checkbox_all;
    TextView tv_download_btn;
    static String course_id="";
    private UpdateVersionUtils updateVersionUtils = null;
    String course_section_id = "";
    public static void start(Context context, String courseid) {
        course_id = courseid;
        Router.newIntent(context)
                .putString("courseid",courseid)
                .to(DownloadCourseActivity.class)
                .launch();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_course);
        updateVersionUtils = new UpdateVersionUtils();
        initView();
    }

    private void initView() {
        ((ImageView)findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.tv_title)).setText("选择下载");
        ((TextView)findViewById(R.id.tv_right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下载
                startActivity(new Intent(DownloadCourseActivity.this,MineDownloadCourseQueryActivity.class));
            }
        });
        tv_download_btn = findViewById(R.id.tv_download_btn);
        checkbox_all = findViewById(R.id.checkbox_all);
        recycler_view = findViewById(R.id.recycler_view);
        sectionBeans = ABaseService.courseDetailInfo.getSection();
        recycler_view.setLayoutManager(new GridLayoutManager(recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));
        downloadCourseAdapter = new RecycleItemDownloadCourseAdapter(this,sectionBeans);
        recycler_view.setAdapter(downloadCourseAdapter);
        checkbox_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    downloadCourseAdapter.setAll(true);
                    downloadCourseAdapter.notifyDataSetChanged();
                }else {
                    downloadCourseAdapter.setAll(false);
                    downloadCourseAdapter.notifyDataSetChanged();
                }
            }
        });
        tv_download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadCourseAdapter.getIsSelected().size()==0){
                    ToastUtils.show(DownloadCourseActivity.this,"请选择要下载的课时!");
                    return;
                }
                int count = 0;
                for (int i = 0; i < sectionBeans.size(); i++) {
                    CourseDetailInfo.SectionBean sectionBean = sectionBeans.get(i);
                    downloadCourseAdapter.getIsSelected();
                    Boolean isChecked = downloadCourseAdapter.getIsSelected().get(sectionBean.getSectionid());
                    if (isChecked != null && isChecked) {
                        course_section_id = sectionBean.getSectionid();
                        addDownload(ABaseService.loginInfo.getUserid(),course_id,course_section_id);
//                        count++;
//                        if (count == 1) {
//                            course_section_id = sectionBean.getSectionid();
//                        }
//                        if (count>1) {
//                            course_section_id += ","+sectionBean.getSectionid();
//                        }
                    }
                }

            }
        });
    }


    @SuppressLint("LongLogTag")
    private void addDownload(String userid,String courseid,String course_section_id) {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("userid", userid);
        reqBody.put("courseid", courseid);
        reqBody.put("course_section_id", course_section_id);
        updateVersionUtils.postByName(Global.GET_ADD_DOWNLOAD_COURSE_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("DownloadCourseActivity","result++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        ToastUtils.show(DownloadCourseActivity.this,jsonObject.getString("msg"));
                    }else{
                        ToastUtils.showError(DownloadCourseActivity.this,jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("DownloadCourseActivity","error++++++++++++++++"+error);
            }
        });

    }

}
