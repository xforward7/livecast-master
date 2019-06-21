package com.aidingyun.ynlive.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;


public class StandpointFragment extends WXBaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Standpoint);

        super.onCreate(savedInstanceState);
    }
}