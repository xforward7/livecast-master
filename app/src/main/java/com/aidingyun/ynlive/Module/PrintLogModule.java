package com.aidingyun.ynlive.Module;

import android.util.Log;
import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

public class PrintLogModule extends WXModule {

    @JSMethod
    public void printLog(String str){
        Toast.makeText(mWXSDKInstance.getContext(), str, Toast.LENGTH_SHORT).show();
    }

    /*****jscallback*****/
    @JSMethod
    public void printLogs(String str, JSCallback callback){
        Log.e("123", str);

        Toast.makeText(mWXSDKInstance.getContext(), str, Toast.LENGTH_SHORT).show();
        Map<String, Object> map = new HashMap<>();
        map.put("hello", "weex");
        callback.invokeAndKeepAlive(map);
        //callback.invoke(map);
    }

    @JSMethod
    public void log(String str){
        Log.e("123", str);
    }
}
