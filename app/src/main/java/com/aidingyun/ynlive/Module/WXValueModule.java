package com.aidingyun.ynlive.Module;

import android.util.Log;

import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.mvp.model.entity.ServicepathModel;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

public class WXValueModule extends WXModule {

    @JSMethod
    public void getToken (JSCallback callback) {
        Map<String, String> data = new HashMap<>();

        String userid = "", token = "";
        if (ABaseService.islogin) {
            userid = ABaseService.loginInfo.getUserid();
            token = ABaseService.token;
        }
        data.put("userid", userid);
        data.put("token", token);

        callback.invokeAndKeepAlive(data);
    }

    @JSMethod
    public void getSiteid (JSCallback callback) {

        String siteid = "";
        if (ABaseService.islogin) {
            siteid = ABaseService.siteid;
        }else{
            siteid = ABaseService.preferences.getString("siteid","");
        }

        Log.d("siteid", siteid);
        callback.invokeAndKeepAlive(siteid);
    }

    @JSMethod
    public void getServicePath (String serviceName, JSCallback callback) {

        String servicePath = "";
        for (ServicepathModel.ServiceDataBean serviceDataBean:ABaseService.servicepathModel.service_data) {
            if (serviceDataBean.getService_name().equals(serviceName)){
                servicePath = serviceDataBean.getService_path();
            }
        }

        Log.d("servicePath", servicePath);
        callback.invokeAndKeepAlive(servicePath);
    }
}
