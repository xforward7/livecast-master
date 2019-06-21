package com.aidingyun.ynlive.mvp.ui.activity.Message;

import android.os.Bundle;

import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;
import com.aidingyun.ynlive.mvp.ui.activity.WXBaseActivity;

public class MessageActivity extends WXBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Mine_Message);

        super.onCreate(savedInstanceState);
    }
}
