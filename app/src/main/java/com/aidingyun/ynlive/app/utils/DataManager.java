package com.aidingyun.ynlive.app.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.aidingyun.core.utils.ThreadManager;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.lzh.easythread.AsyncCallback;

import java.io.File;
import java.math.BigDecimal;

/**
 * 计算和清除缓存
 */
public class DataManager {

    public static String getTotalCacheSize(Context context) {
        long cacheSize = 0;
        try {
            cacheSize = getFolderSize(context.getCacheDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                cacheSize += getFolderSize(context.getExternalCacheDir());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除本应用所有的数据
     */
    public static void cleanApplicationData(Context context, @NonNull ClearCompleteListener listener, String... filepath) {
        ThreadManager.getIO().async(() -> {
            cleanInternalCache(context);
            cleanExternalCache(context);
            //cleanSharedPreference(context);
            cleanFiles(context);
            cleanWebview(context);//清除本应用webview缓存
            //cleanDatabaseByName(context, GlobalUtils.DATABASE_NAME);
            if (filepath != null) {
                for (String filePath : filepath) {
                    cleanCustomCache(filePath);
                }
            }
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            imagePipeline.clearCaches();

            Glide.get(context).clearDiskCache();
            return true;
        }, new AsyncCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                listener.clearComplete();
            }

            @Override
            public void onFailed(Throwable t) {

            }
        });


    }

    public interface ClearCompleteListener {
        void clearComplete();
    }

    /**
     * * 清除本应用webview缓存
     *
     * @param context
     */
    public static void cleanWebview(Context context) {
        deleteDir(new File("/data/data/"
                + context.getPackageName() + "/app_webview"));
    }

    /**
     * * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * *
     *
     * @param dir
     */
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();

            if (fileList == null) {
                return 0;
            }
            for (File aFileList : fileList) {
                // 如果下面还有文件
                if (aFileList == null) {
                    continue;
                }
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     */
    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }


    /***
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     */
    private static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /***
     * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     */
    private static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * 按名字清除本应用数据库
     */
    private static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * 清除/data/data/com.xxx.xxx/files
     */
    private static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     */
    private static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     */
    private static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /***
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
