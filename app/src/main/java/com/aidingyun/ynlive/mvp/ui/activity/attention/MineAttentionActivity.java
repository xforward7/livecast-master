package com.aidingyun.ynlive.mvp.ui.activity.attention;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.MineAttentionModel;
import com.aidingyun.ynlive.mvp.ui.adapter.attention.AttentionRecycleAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;
import java.util.concurrent.ConcurrentSkipListMap;

public class MineAttentionActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    AttentionRecycleAdapter attentionRecycleAdapter;
    private UpdateVersionUtils updateVersionUtils = null;
    MineAttentionModel attentionModel;
    int currentPage = 1;

    SmartRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_attention);

        initView();
        getAttentionList();
    }

    private void initView() {
        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView) findViewById(R.id.tv_title)).setText("我的关注");
        updateVersionUtils = new UpdateVersionUtils();
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new GridLayoutManager(recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));

        refreshLayout = findViewById(R.id.refreshLayout);

        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currentPage = 1;
                getAttentionList();
            }
        });

        // 上拉加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (attentionModel.getData().getTotalPage() > 1 && attentionModel.getData().getTotalPage() > currentPage) {
                    currentPage ++;
                    getAttentionList();
                } else  {
                    if (refreshLayout.getState() == RefreshState.Loading) {
                        refreshLayout.finishLoadMore();
                    }
                    Toast.makeText(MineAttentionActivity.this, "已经是最后一页", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getAttentionList() {

        updateVersionUtils.postByName(Global.GET_MINE_ATTENTION_SERVICE_NAME, new ConcurrentSkipListMap<>(), new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {

                if (refreshLayout.getState() == RefreshState.Refreshing) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.getState() == RefreshState.Loading) {
                    refreshLayout.finishLoadMore();
                }

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                        Gson gson = new Gson();
                        attentionModel = gson.fromJson(result, MineAttentionModel.class);
                        attentionRecycleAdapter = new AttentionRecycleAdapter(MineAttentionActivity.this, attentionModel);
                        recycler_view.setAdapter(attentionRecycleAdapter);
                    } else {
                        if (currentPage > 1) currentPage --;
                        ToastUtils.showError(MineAttentionActivity.this,jsonObject.getString("msg"));
                    }
                } catch (Exception e){
                    if (currentPage > 1) currentPage --;
                    e.getMessage();
                }
            }

            @Override
            public void onError(String error) {

                if (refreshLayout.getState() == RefreshState.Refreshing) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.getState() == RefreshState.Loading) {
                    refreshLayout.finishLoadMore();
                }

                if (currentPage > 1) currentPage --;

                ToastUtils.showError(MineAttentionActivity.this, error);
                Log.e("MineAttentionActivity","error++++++++++++++++"+error);
            }
        });

    }
}