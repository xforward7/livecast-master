package com.aidingyun.ynlive.app.utils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ParamInterceptor implements Interceptor {

    public ParamInterceptor() {
        //添加全局的请求参数
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        httpUrlBuilder.addQueryParameter("", "");//添加参数
        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.url(httpUrlBuilder.build());
        request = requestBuilder.build();
        return chain.proceed(request);
    }

}