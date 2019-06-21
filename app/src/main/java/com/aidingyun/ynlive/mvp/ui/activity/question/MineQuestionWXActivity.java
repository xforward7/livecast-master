package com.aidingyun.ynlive.mvp.ui.activity.question;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;
import com.aidingyun.ynlive.mvp.ui.activity.WXBaseActivity;

public class MineQuestionWXActivity extends WXBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Mine_Question);

        super.onCreate(savedInstanceState);
    }
}
