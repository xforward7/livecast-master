package com.aidingyun.ynlive.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;

public class HobbyActivity extends WXBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Interests_hobbies);

        super.onCreate(savedInstanceState);
    }
}
