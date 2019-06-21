package com.aidingyun.ynlive.mvp.ui.activity.course_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.mvp.contract.WXRenderUrls;
import com.aidingyun.ynlive.mvp.ui.activity.WXBaseActivity;

public class CourseDetailWXActivity extends WXBaseActivity {

    static String course_id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        configureWXRenderURL(WXRenderUrls.WXRenderURL_Course_detail);

        super.onCreate(savedInstanceState);
    }

    public static void start(Context context, String courseid) {
        course_id = courseid;
        Router.newIntent(context)
                .putString("courseid",courseid)
                .to(CourseDetailWXActivity.class)
                .launch();
    }
}
