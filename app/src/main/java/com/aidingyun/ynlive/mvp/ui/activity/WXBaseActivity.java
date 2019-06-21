package com.aidingyun.ynlive.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.aidingyun.ynlive.R;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

public class WXBaseActivity extends Activity implements IWXRenderListener {

    private WXSDKInstance mWXSDKInstance;
    private String mWXRenderURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpage);

        if (mWXRenderURL != null) {
            mWXSDKInstance = new WXSDKInstance(this);
            mWXSDKInstance.registerRenderListener(this);

            mWXSDKInstance.renderByUrl("WX_Page", mWXRenderURL, null, null, WXRenderStrategy.APPEND_ASYNC);
        }
    }

    public void configureWXRenderURL (String renderURL) {
        mWXRenderURL = renderURL;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityResume();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityPause();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityStop();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityDestroy();
        }
    }

    // about weex
    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        // 处理view的加载 以及大小
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        // 渲染成功
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
        // 刷新成功
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        // 加载weex页面失败 使用webview降级处理
    }
}
