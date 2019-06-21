/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aidingyun.ynlive.mvp.model.api;

import com.aidingyun.ynlive.mvp.model.entity.ListInfo;
import com.aidingyun.ynlive.mvp.model.entity.LoginResponse;
import com.aidingyun.ynlive.mvp.model.entity.base.BaseStatus;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by JessYan on 08/05/2016 11:25
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
 String APP_DOMAIN = "http://codebank.iidingyun.com/app/app_init.vm?appid=yenei2";
    //  String APP_DOMAIN = "http://8694880.iidingyun.com/get_siteid.vm";
   // String PACK = "com.aidingyun.ynlive";
    int RequestSuccess = 0;

    public static String DUBUG_KEY = APP_DOMAIN;//测试服务器
    public static String RELEASE_KEY = "http://8694880.iidingyun.com/get_siteid.vm";//正式服务器

    public static String SERVICE_URL = "http://s3.iidingyun.com/api/get_service_to_json.vm";//微服务地址
    public static String LOGIN_URL = "";
//
//
//    // 线上
//    String HOST = "http://dw.qianbao666.com/";
//
//
//    /**
//     * 获取注册验证码
//     */
//    @FormUrlEncoded
//    @POST(HOST + "app/sendSMS.do")
//    Call<BaseStatus> getVerifyCode(@Field("username") String username, @Field("password") String pwd);
//
//    /**
//     * 用户登录
//     */
//    @FormUrlEncoded
//    @POST(HOST + "app/login.do")
//    Call<LoginResponse> login(@Field("username") String username, @Field("password") String pwd, @Field("code") String verifyCode);

    /**
     * 获取列表数据
     */
//    @GET("http://baoyue.qbao.com/app/task/list.json")
//    Call<ListInfo> getList(@Query("type") int type, @Query("page") int page, @Query("rows") int rows);
}
