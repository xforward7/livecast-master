package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.greendao.WatchRecordDao;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.HistoryBean;
import com.aidingyun.ynlive.mvp.model.entity.LiveWatchBean;
import com.aidingyun.ynlive.mvp.ui.adapter.history.WatchRecycleItemAdapter;
import com.google.gson.Gson;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class YNRecordActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private WatchRecordDao watchRecordDao;
    private UpdateVersionUtils updateVersionUtils = null;
    private List<LiveWatchBean> mWatchRecordList = new ArrayList<>();
    WatchRecycleItemAdapter watchRecycleItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ynrecord);

//        updateVersionUtils = new UpdateVersionUtils();
        initView();
        watchRecordDao = new WatchRecordDao(this);
//        getWatchHistory();
        getWatchRecordData();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.VERTICAL, false));
        ((TextView)findViewById(R.id.title)).setText("观看历史");
        ((ImageView)findViewById(R.id.left_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.tv_delete_history_btne)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchRecordDao.deleteAllRecord();
            }
        });
    }


    private void getWatchRecordData()
    {
        mWatchRecordList.clear();
        mWatchRecordList = watchRecordDao.getWatchRecordList();
//        mWatchRecordAdapter.updateData(mWatchRecordList);
        ToastUtils.show(YNRecordActivity.this,"历史："+mWatchRecordList.size());
    }

    private void getWatchHistory() {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("op", "pull");
        updateVersionUtils.postByName(Global.GET_WATCH_HISTORY_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("YNRecordActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        Gson gson = new Gson();
                        HistoryBean historyBean = gson.fromJson(result,HistoryBean.class);
                        watchRecycleItemAdapter = new WatchRecycleItemAdapter(YNRecordActivity.this,historyBean.getList());
                        recyclerView.setAdapter(watchRecycleItemAdapter);
                    }else{
                        ToastUtils.showError(YNRecordActivity.this,jsonObject.getString("msg"));
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
