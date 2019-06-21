package com.aidingyun.ynlive.component.log.trace;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.aidingyun.ynlive.app.utils.ProcessUtils;

import java.io.File;

public class TracerConfig {
    public static final long MEMORY_SIZE = 8 * 1024;
    public static final long LOG_FLUSH_DURATION = 10 * 1000;

    public static final String DEF_TRACE_FILEEXT = ".log";
    private static String DEF_TRACE_FILEPRE;
    public static final int MAX_BLOCK_COUNT = 12;
    public static final int MAX_BLOCK_SIZE = 256 * 1024;

    /**
     * @param context
     * @return
     */
    public static final String getLogDir(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "android" + File.separator + "data"
                + File.separator + context.getPackageName() + File.separator + "logs";
    }

    /**
     * @param context
     * @return
     */
    public static final String getLogFilePre(Context context) {
        if (!TextUtils.isEmpty(DEF_TRACE_FILEPRE))
            return DEF_TRACE_FILEPRE;

        String pre = "";
        if (ProcessUtils.isMainProcess(context)) {
            pre = "";
        } else {
            String processName = ProcessUtils.myProcessName(context);
            int index = processName.indexOf(":");
            if (index >= 0) {
                pre = processName.substring(index + 1) + ".";
            }
        }
        return pre;
    }
}
