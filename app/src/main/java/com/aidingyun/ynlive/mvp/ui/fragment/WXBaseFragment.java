package com.aidingyun.ynlive.mvp.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.aidingyun.ynlive.R;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;


public class WXBaseFragment extends Fragment implements IWXRenderListener {

    private FrameLayout mContainer;
    WXSDKInstance mWXSDKInstance;
    private String mWXRenderURL;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getActivity(), R.layout.fragement_weex, null);
        mContainer = (FrameLayout) view.findViewById(R.id.weex_fragment_container);

        if (mWXRenderURL != null) {
            mWXSDKInstance = new WXSDKInstance(getActivity());
            mWXSDKInstance.registerRenderListener(this);

            /**
             * WXSample 可以替换成自定义的字符串，针对埋点有效。
             * url 渲染view的url。
             * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
             * jsonInitData 可以为空。
             * flag WXRenderStrategy 枚举
             */
//        mWXSDKInstance.render("WXSample", WXFileUtils.loadFileContent("bundle.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mContainer.getParent() != null) {
            ((ViewGroup) mContainer.getParent()).removeView(mContainer);
        }
        return mContainer;
    }

    // about weex
    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        // 处理view的加载 以及大小
        mContainer.addView(view);
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