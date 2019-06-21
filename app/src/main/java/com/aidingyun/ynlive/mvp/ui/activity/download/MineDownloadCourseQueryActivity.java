package com.aidingyun.ynlive.mvp.ui.activity.download;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.ui.adapter.download.DownloadListAdapter;
import com.aidingyun.ynlive.mvp.ui.base.BaseActivity;
import com.google.gson.Gson;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class MineDownloadCourseQueryActivity extends BaseActivity {
//    @Bind(R.id.tv_memory_used)
//    TextView tv_memory_used;
//    @Bind(R.id.tv_memory_residue)
//    TextView tv_memory_residue;
//    @Bind(R.id.tv_download_btn)
//    TextView tv_download_btn;
//    @Bind(R.id.tv_delete_btn)
//    TextView tv_delete_btn;
//    @Bind(R.id.recycler_view)
//    RecyclerView recycler_view;
    TextView tv_memory_used;//已用内存
    TextView tv_memory_residue;//剩余内存
    TextView tv_download_btn;//下载按钮
    TextView tv_delete_btn;//删除按钮
    TextView tv_right;//
    ExpandableListView elv_collocation;
    private UpdateVersionUtils updateVersionUtils = null;
    DownloadListAdapter downloadListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_download_course_query);
        updateVersionUtils = new UpdateVersionUtils();
        initView();
        getDownloadList(ABaseService.loginInfo.getUserid(),"");
    }

    private void initView() {
        ((ImageView)findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.tv_title)).setText("我的下载");
        ((TextView)findViewById(R.id.tv_right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_right)).setText("全选");

        tv_memory_used = findViewById(R.id.tv_memory_used);
        tv_right = findViewById(R.id.tv_right);
        tv_memory_residue = findViewById(R.id.tv_memory_residue);
        tv_download_btn = findViewById(R.id.tv_download_btn);
        tv_delete_btn = findViewById(R.id.tv_delete_btn);
        elv_collocation = findViewById(R.id.elv_collocation);
        downloadListAdapter = new DownloadListAdapter(MineDownloadCourseQueryActivity.this,elv_collocation,tv_right,tv_download_btn,tv_delete_btn);
        elv_collocation.setAdapter(downloadListAdapter);


        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_right.getText().toString().equals("取消全选")){
                    downloadListAdapter.setGroupCheck(false);
                    downloadListAdapter.setChildCheck(false);
                    tv_right.setText("全选");
                }else {
                    downloadListAdapter.setGroupCheck(true);
                    downloadListAdapter.setChildCheck(true);
                    tv_right.setText("取消全选");
                }
                    downloadListAdapter.notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("LongLogTag")
    private void getDownloadList(String userid,String courseid) {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("userid", userid);
        reqBody.put("courseid", courseid);
        updateVersionUtils.postByName(Global.GET_DOWNLOAD_COURSE_LIST_QUERY_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("MineDownloadCourseQueryActivity","result++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        Gson gson = new Gson();
//                        CourseOrderInfo courseOrderInfo = gson.fromJson(result,CourseOrderInfo.class);
                        downloadListAdapter = new DownloadListAdapter(MineDownloadCourseQueryActivity.this,elv_collocation,tv_right,tv_download_btn,tv_delete_btn);
                        elv_collocation.setAdapter(downloadListAdapter);
                    }else{
                        ToastUtils.showError(MineDownloadCourseQueryActivity.this,jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("HasPaymentFragment","error++++++++++++++++"+error);
            }
        });

    }
}
