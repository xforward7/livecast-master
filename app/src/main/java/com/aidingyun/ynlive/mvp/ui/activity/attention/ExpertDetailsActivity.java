package com.aidingyun.ynlive.mvp.ui.activity.attention;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;
import com.aidingyun.ynlive.mvp.ui.activity.WXBaseActivity;

public class ExpertDetailsActivity extends WXBaseActivity {

    static String expert_id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Expert_detail);

        super.onCreate(savedInstanceState);
    }

    public static void start(Context context, String userid) {
        expert_id = userid;
        Router.newIntent(context)
                .putString("expert_id",userid)
                .to(ExpertDetailsActivity.class)
                .launch();
    }
}
