package com.aidingyun.webview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.IWebLayout;

/**
 * Created by cenxiaozhong on 2017/7/1.
 * source code  https://github.com/Justson/AgentWeb
 */

public class WebLayout implements IWebLayout {

    private final LinearLayout mLinearLayout;
    private Activity mActivity;
    private WebView mWebView = null;

    public WebLayout(Activity activity) {
        this.mActivity = activity;
        mLinearLayout = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.fragment_web, null);
        mWebView = mLinearLayout.findViewById(R.id.webView);
    }

    @NonNull
    @Override
    public ViewGroup getLayout() {
        return mLinearLayout;
    }

    @Nullable
    @Override
    public WebView getWebView() {
        return mWebView;
    }

}
