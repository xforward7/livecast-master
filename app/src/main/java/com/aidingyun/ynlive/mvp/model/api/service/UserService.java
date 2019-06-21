package com.aidingyun.ynlive.mvp.model.api.service;

import com.aidingyun.ynlive.mvp.model.entity.base.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * ================================================
 * 展示 {@link Retrofit#create(Class)} 中需要传入的 ApiService 的使用方式
 * 存放关于用户的一些 API
 */
public interface UserService {

    @POST("")
    Observable<BaseResponse> post(@Body RequestBody body);

    @POST("")
    Observable<ResponseBody> get(@Body RequestBody body);

}
