package com.aidingyun.ynlive.mvp.ui.activity.SystemSetting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;
import com.aidingyun.ynlive.mvp.ui.activity.WXBaseActivity;

public class SystemSettingWXActivity extends WXBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_System_setting);

        super.onCreate(savedInstanceState);
    }
}
