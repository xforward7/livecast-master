package com.aidingyun.ynlive.app.utils;

import android.app.Activity;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.component.app.GlobalContext;
import com.aidingyun.ynlive.mvp.model.api.Api;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     project name: Ruisi
 *     author      : 翁嘉若
 *     create time : 2017/10/6 上午12:29
 *     desc        : 描述--//UpdateUtils 更新工具
 * </pre>
 */


public class UpdateUtils extends UpdateCallback {

    private UpdateUtils() {
    }

    public static UpdateUtils getInstance() {
        return UpdateUtilsHolder.instance;
    }

    /**
     * 升级接口
     *
     * @param mActivity
     * @param hiddenHasIgnored 是否隐藏已经忽略掉的版本
     * @return
     */
    public void checkUpdate(Activity mActivity, boolean hiddenHasIgnored) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        Map<String, String> params = new HashMap<>();

        UpdateAppManager.Builder builder = new UpdateAppManager.Builder()
                .setActivity(mActivity)
                .setHttpManager(new UpdateVersionUtils())
                .setUpdateUrl(Api.APP_DOMAIN + "")
                .setThemeColor(ContextCompat.getColor(GlobalContext.getAppContext(), R.color.colorAccent))
                .setPost(true)
                .setParams(params)
                .setTargetPath(path);
        if (hiddenHasIgnored) {
            builder.showIgnoreVersion();
        }
        builder.build().checkNewApp(this);
    }

    @Override
    protected UpdateAppBean parseJson(String json) {
        return new UpdateAppBean();
    }

    @Override
    public void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
        if (updateApp.isConstraint() && updateApp.isOnlyWifi()) {
            updateAppManager.download();//强制更新
        } else {
            updateAppManager.showDialogFragment();//更新工具自带对话框
            result.ignoreApp();
        }
        result.hasNewApp();
    }

    @Override
    protected void onAfter() {
    }

    @Override
    protected void noNewApp(String error) {
        result.noNewApp(error);
    }

    private static class UpdateUtilsHolder {
        static UpdateUtils instance = new UpdateUtils();
    }

    private CheckUpdateResult result;

    public UpdateUtils setCheckResultListener(CheckUpdateResult result) {
        this.result = result;
        return this;
    }

    public interface CheckUpdateResult {

        /**
         * 有新版本
         */
        void hasNewApp();

        /**
         * 没有最新版本
         */
        void noNewApp(String error);

        void ignoreApp();
    }
}
