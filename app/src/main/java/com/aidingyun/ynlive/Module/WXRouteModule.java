package com.aidingyun.ynlive.Module;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.aidingyun.ynlive.component.app.GlobalContext;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import java.util.Map;

public class WXRouteModule extends WXModule {

    private Activity mActivity;
    private String activityPath = "com.aidingyun.ynlive.mvp.ui.activity.";

    @JSMethod
    public void startPage (String pageName, Map<String, String> map) {
        Log.e("pageName", pageName);

        try {
            mActivity = GlobalContext.getCurrActivity();
            String activityName = activityPath + pageName;
            Intent intent = new Intent(mActivity, Class.forName(activityName));
            mActivity.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @JSMethod
    public void fromPage (String pageName, Map<String, String> map) {
        Log.e("from", pageName);

        try {
            mActivity = GlobalContext.getCurrActivity();
            String activityName = activityPath + pageName;
            Intent intent = new Intent(mActivity, Class.forName(activityName));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mActivity.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
