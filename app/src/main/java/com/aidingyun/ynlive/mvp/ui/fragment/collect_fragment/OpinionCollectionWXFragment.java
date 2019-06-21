package com.aidingyun.ynlive.mvp.ui.fragment.collect_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;
import com.aidingyun.ynlive.mvp.ui.fragment.WXBaseFragment;

public class OpinionCollectionWXFragment extends WXBaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Mine_Collection_Opinion);

        super.onCreate(savedInstanceState);
    }

    public static OpinionCollectionWXFragment newInstance() {

        Bundle args = new Bundle();

        OpinionCollectionWXFragment fragment = new OpinionCollectionWXFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
