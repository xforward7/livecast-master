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
package com.aidingyun.ynlive.app.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.model.entity.ServicepathModel;
import com.aidingyun.ynlive.mvp.model.entity.LoginInfo;

/**
 * ================================================
 * 展示 {@link com.jess.arms.base.BaseService} 的用法
 * <p>
 * Created by JessYan on 09/07/2016 16:59
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ABaseService extends com.jess.arms.base.BaseService {
   public static ServicepathModel servicepathModel;
   public static CourseDetailInfo courseDetailInfo;
   public static CourseClassificationModel courseClassificationModel;
   public static HomeCourseModel homeCourseModel;
   public static LoginInfo loginInfo;
   public static boolean islogin;
   public static String token;
   public static String siteid;
   public static SharedPreferences preferences;
    @Override
    public void init() {
        preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
    }
}
