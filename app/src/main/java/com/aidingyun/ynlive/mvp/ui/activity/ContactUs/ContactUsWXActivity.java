package com.aidingyun.ynlive.mvp.ui.activity.ContactUs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;
import com.aidingyun.ynlive.mvp.ui.activity.WXBaseActivity;

public class ContactUsWXActivity extends WXBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Contact_us);

        super.onCreate(savedInstanceState);
    }
}
