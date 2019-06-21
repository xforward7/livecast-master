package com.aidingyun.ynlive.mvp.ui.activity.mine_learn;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;
import com.aidingyun.ynlive.mvp.ui.activity.WXBaseActivity;

public class MineLearnActivity extends WXBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Mine_Study);

        super.onCreate(savedInstanceState);
    }
}
