package com.aidingyun.ynlive.app.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.ServicepathModel;
import com.aidingyun.ynlive.mvp.ui.activity.account.PayActivity;
import com.facebook.stetho.common.LogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.vector.update_app.HttpManager;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;


/**
 * 使用OkGo实现接口
 */

public class UpdateVersionUtils implements HttpManager {
    private static UpdateVersionUtils instance = null;
    public static UpdateVersionUtils newInstance()
    {
        if(instance == null)
        {
            instance = new UpdateVersionUtils();
        }
        return instance;
    }

    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
        asyncPost(url, params, callBack);
//        String siteid = "";
//        if (ABaseService.islogin) {
//            siteid = ABaseService.siteid;
//        }else{
//            siteid = ABaseService.preferences.getString("siteid","");
//        }
//        OkGo.<String>get(url+"?siteid="+siteid)
//                .tag(this)
//                .cacheKey("cacheKey") // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
////                .cacheTime(5000) // 缓存的过期时间,单位毫秒
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//设置缓存模式
//                .execute(new com.lzy.okgo.callback.StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        callBack.onResponse(response.body());
//
//                    }
//
//                    @Override
//                    public void onError(com.lzy.okgo.model.Response<String> response) {
//                        super.onError(response);
//                        callBack.onError(response.body());
//                    }
//                });
        /**
         * 同步请求
         */
        //方式一:
//        try {
//            okhttp3.Response response = OkGo.<String>get(url)
//                .params("adKind", "1")
//                .execute();
//            Log.d("meee",getClass()+":\n"+"test:"+response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //方式二,也许可以转换成Rxjava的Obser
//        Call<String> call = OkGo.<String>get(url)
//                .params("adKind", "1")
//                .converter(new StringConvert())
//                .adapt();
//        try {
//            Response<String> execute = call.execute();
//            Log.d("meee",getClass()+":\n"+"execute:"+execute.body());
//        } catch (Exception e) {
//            e.printStackTrace();
//
//             }
    }
//private static StringBuilder sb=new StringBuilder();
    public void postByName(@NonNull String name, @NonNull Map<String, String> params, @NonNull final Callback callBack){
        //sb.append(name).append(",");

       // Log.e("================",sb.toString());
        String url = "";
        String siteid = "";
        for (ServicepathModel.ServiceDataBean serviceDataBean:ABaseService.servicepathModel.service_data) {
            if (serviceDataBean.getService_name().equals(name)){
                url = serviceDataBean.getService_path();
               // Log.e("postByName","url++++++++++++++++"+url);
            }
        }
//        if(url==null&&url.equals(""))
//        {
//            Log.e(name,name+" service not found");
//        }

        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept-Language","zh-CN");
        if (ABaseService.islogin) {
            headers.put("Authorization", ABaseService.token);
        }
            siteid = ABaseService.siteid;
        Log.e("postByName","service name=>"+name+">>>url>>>>>>>>>>>>"+url+params+",siteid="+siteid);
        OkGo.<String>post(url)
                .tag(this)
                .headers(headers)
                .params(params)
                .params("siteid", siteid)
                .cacheKey("cacheKey") // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheTime(5000) // 缓存的过期时间,单位毫秒
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//设置缓存模式
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onResponse(response.body());
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        callBack.onError(response.body());
                    }
                });
    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {

        Log.e("post by url", " url>>>>>>>>>>>>"+url+params);

        String siteid = "";
        HttpHeaders headers = new HttpHeaders();
        if (ABaseService.islogin) {
            headers.put("Authorization", ABaseService.token);
            siteid = ABaseService.siteid;
        }else{
            siteid = ABaseService.preferences.getString("siteid","");
        }

        OkGo.<String>post(url)
                .tag(this)
                .headers(headers)
                .params(params)
                .params("siteid", siteid)
                .cacheKey("cacheKey") // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheTime(5000) // 缓存的过期时间,单位毫秒
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//设置缓存模式
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {


                        System.out.print("result["+response.body()+"]");
                        callBack.onResponse(response.body());
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        callBack.onError(response.body());
                    }
                });
    }

    /**
     * 保存数据到服务器
     */
    public void uoloadIcon(String url,
                           @NonNull Map<String, String> params,
                            @NonNull final Callback callBack) {
        OkGo.<String>post(url)
                .tag(this)
                .isMultipart(true)
                .params(params)
//                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("上传成功" + response.body());
                        callBack.onResponse(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        LogUtil.e("上传失败" + response.body());
                        callBack.onResponse(response.body());
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);

                    }
                });
    }

    @Override
    public void download(@NonNull String url,
                         @NonNull String path,
                         @NonNull String fileName,
                         @NonNull final FileCallback callback) {
        OkGo.<File>get(url)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//设置缓存模式
                .cacheKey("cacheKey")//作为缓存的key
                .execute(new com.lzy.okgo.callback.FileCallback(path, fileName) {
                    @Override
                    public void onSuccess(Response<File> response) {
                        callback.onResponse(response.body());
                    }

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        callback.onBefore();
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        callback.onError("异常");
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);

                        callback.onProgress(progress.fraction, progress.totalSize);
                    }
                });
    }




}