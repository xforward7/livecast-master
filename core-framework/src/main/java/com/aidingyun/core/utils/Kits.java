package com.aidingyun.core.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;

import com.lzh.easythread.AsyncCallback;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by wanglei on 2016/11/28.
 */

public class Kits {

    public static class Package {
        /**
         * 获取版本号
         *
         * @param context
         * @return
         */
        public static int getVersionCode(Context context) {
            PackageManager pManager = context.getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = pManager.getPackageInfo(context.getPackageName(), 0);

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return packageInfo.versionCode;
        }

        /**
         * 获取当前版本
         *
         * @param context
         * @return
         */
        public static String getVersionName(Context context) {
            PackageManager pManager = context.getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = pManager.getPackageInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return packageInfo.versionName;
        }

        /**
         * 判断是否是系统App
         *
         * @param context
         * @param packageName 包名
         * @return
         */
        public static boolean isSystemApplication(Context context, String packageName) {
            if (context == null) {
                return false;
            }
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || packageName == null || packageName.length() == 0) {
                return false;
            }

            try {
                ApplicationInfo app = packageManager.getApplicationInfo(packageName, 0);
                return (app != null && (app.flags & ApplicationInfo.FLAG_SYSTEM) > 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }

        /**
         * 判断某个包名是否运行在顶层
         *
         * @param context
         * @param packageName
         * @return
         */
        public static Boolean isTopActivity(Context context, String packageName) {
            if (context == null || TextUtils.isEmpty(packageName)) {
                return null;
            }

            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
            if (tasksInfo == null || tasksInfo.isEmpty()) {
                return null;
            }
            try {
                return packageName.equals(tasksInfo.get(0).topActivity.getPackageName());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 获取Meta-Data
         *
         * @param context
         * @param key
         * @return
         */
        public static String getAppMetaData(Context context, String key) {
            if (context == null || TextUtils.isEmpty(key)) {
                return null;
            }
            String resultData = null;
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                    if (applicationInfo != null) {
                        if (applicationInfo.metaData != null) {
                            resultData = applicationInfo.metaData.getString(key);
                        }
                    }

                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            return resultData;
        }

        /**
         * 获取当前进程名称
         *
         * @return processName
         */
        public static String getCurrentProcessName(Context context) {
            int currentProcessId = android.os.Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
                if (runningAppProcess.pid == currentProcessId) {
                    return runningAppProcess.processName;
                }
            }
            return "";
        }

        /**
         * 判断当前应用是否运行在后台
         *
         * @param context
         * @return
         */
        public static boolean isApplicationInBackground(Context context) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
            if (taskList != null && !taskList.isEmpty()) {
                ComponentName topActivity = taskList.get(0).topActivity;
                return topActivity != null && !topActivity.getPackageName().equals(context.getPackageName());
            }
            return false;
        }
    }


    public static class Dimens {
        public static final int UNIT_DP = 1;
        public static final int UNIT_PX = 2;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({UNIT_DP, UNIT_PX})
        public @interface DimensUnit {

        }

        public static float dpToPx(Context context, float dp) {
            return dp * context.getResources().getDisplayMetrics().density;
        }

        public static float pxToDp(Context context, float px) {
            return px / context.getResources().getDisplayMetrics().density;
        }

        public static int dpToPxInt(Context context, float dp) {
            return (int) (dpToPx(context, dp) + 0.5f);
        }

        public static int pxToDpCeilInt(Context context, float px) {
            return (int) (pxToDp(context, px) + 0.5f);
        }

        public static DisplayMetrics getDisplayMetrics(Context context) {
            return context.getResources().getDisplayMetrics();
        }

        /**
         * 通过反射，获取包含虚拟键的整体屏幕高度
         *
         * @return
         */
        public static int getVirtualKeyboard(Activity context) {
            int dpi = 0;
            Display display = context.getWindowManager().getDefaultDisplay();
            DisplayMetrics dm = new DisplayMetrics();
            @SuppressWarnings("rawtypes")
            Class c;
            try {
                c = Class.forName("android.view.Display");
                @SuppressWarnings("unchecked")
                Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, dm);
                dpi = dm.heightPixels;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dpi;
        }
    }


    public static class Random {
        public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static final String NUMBERS = "0123456789";
        public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

        public static String getRandomNumbersAndLetters(int length) {
            return getRandom(NUMBERS_AND_LETTERS, length);
        }

        public static String getRandomNumbers(int length) {
            return getRandom(NUMBERS, length);
        }

        public static String getRandomLetters(int length) {
            return getRandom(LETTERS, length);
        }

        public static String getRandomCapitalLetters(int length) {
            return getRandom(CAPITAL_LETTERS, length);
        }

        public static String getRandomLowerCaseLetters(int length) {
            return getRandom(LOWER_CASE_LETTERS, length);
        }

        public static String getRandom(String source, int length) {
            return TextUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
        }

        public static String getRandom(char[] sourceChar, int length) {
            if (sourceChar == null || sourceChar.length == 0 || length < 0) {
                return null;
            }

            StringBuilder str = new StringBuilder(length);
            java.util.Random random = new java.util.Random();
            for (int i = 0; i < length; i++) {
                str.append(sourceChar[random.nextInt(sourceChar.length)]);
            }
            return str.toString();
        }

        public static int getRandom(int max) {
            return getRandom(0, max);
        }

        public static int getRandom(int min, int max) {
            if (min > max) {
                return 0;
            }
            if (min == max) {
                return min;
            }
            return min + new java.util.Random().nextInt(max - min);
        }
    }

    public static class File {
        public final static String FILE_EXTENSION_SEPARATOR = ".";

        /**
         * read file
         *
         * @param filePath
         * @param charsetName The name of a supported {@link java.nio.charset.Charset </code>charset<code>}
         * @return if file not exist, return null, else return content of file
         * @throws RuntimeException if an error occurs while operator BufferedReader
         */
        public static StringBuilder readFile(String filePath, String charsetName) {
            java.io.File file = new java.io.File(filePath);
            StringBuilder fileContent = new StringBuilder("");
            if (file == null || !file.isFile()) {
                return null;
            }

            BufferedReader reader = null;
            try {
                InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
                reader = new BufferedReader(is);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (!fileContent.toString().equals("")) {
                        fileContent.append("\r\n");
                    }
                    fileContent.append(line);
                }
                return fileContent;
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                IO.close(reader);
            }
        }

        //读取方法
        public static String getJson(Context context, String fileName) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                AssetManager assetManager = context.getAssets();
                BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
                String line;
                while ((line = bf.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        /**
         * write file
         *
         * @param filePath
         * @param content
         * @param append   is append, if true, write to the end of file, else clear content of file and write into it
         * @return return false if content is empty, true otherwise
         * @throws RuntimeException if an error occurs while operator FileWriter
         */
        public static boolean writeFile(String filePath, String content, boolean append) {
            if (TextUtils.isEmpty(content)) {
                return false;
            }

            FileWriter fileWriter = null;
            try {
                makeDirs(filePath);
                fileWriter = new FileWriter(filePath, append);
                fileWriter.write(content);
                return true;
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                IO.close(fileWriter);
            }
        }

        public static void savePhoto(final Activity activity, final Bitmap bmp, final SaveResultCallback saveResultCallback) {
            final java.io.File sdDir = getSDPath();
            if (sdDir == null) {
                return;
            }
            ThreadManager.getIO().async(new Callable<java.io.File>() {
                @Override
                public java.io.File call() throws Exception {

                    java.io.File appDir = new java.io.File(sdDir, "out_photo");
                    if (!appDir.exists()) {
                        appDir.mkdir();
                    }
                    String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";
                    java.io.File file = new java.io.File(appDir, fileName);
                    FileOutputStream fos = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                    return file;
                }
            }, new AsyncCallback<java.io.File>() {
                @Override
                public void onSuccess(java.io.File file) {
                    Uri uri = Uri.fromFile(file);//保存图片后发送广播通知更新数据库
                    activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                    saveResultCallback.onSavedSuccess(file.getAbsolutePath());
                }

                @Override
                public void onFailed(Throwable t) {
                    saveResultCallback.onSavedFailed();
                }
            });
        }

        public static java.io.File getSDPath() {
            java.io.File sdDir = null;
            boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
            if (sdCardExist) {
                sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            }
            return sdDir;
        }

        /**
         * write file
         *
         * @param filePath
         * @param contentList
         * @param append      is append, if true, write to the end of file, else clear content of file and write into it
         * @return return false if contentList is empty, true otherwise
         * @throws RuntimeException if an error occurs while operator FileWriter
         */
        public static boolean writeFile(String filePath, List<String> contentList, boolean append) {
            if (contentList == null || contentList.isEmpty()) {
                return false;
            }

            FileWriter fileWriter = null;
            try {
                makeDirs(filePath);
                fileWriter = new FileWriter(filePath, append);
                int i = 0;
                for (String line : contentList) {
                    if (i++ > 0) {
                        fileWriter.write("\r\n");
                    }
                    fileWriter.write(line);
                }
                return true;
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                IO.close(fileWriter);
            }
        }

        /**
         * write file, the string will be written to the begin of the file
         *
         * @param filePath
         * @param content
         * @return
         */
        public static boolean writeFile(String filePath, String content) {
            return writeFile(filePath, content, false);
        }

        /**
         * write file, the string list will be written to the begin of the file
         *
         * @param filePath
         * @param contentList
         * @return
         */
        public static boolean writeFile(String filePath, List<String> contentList) {
            return writeFile(filePath, contentList, false);
        }

        /**
         * write file, the bytes will be written to the begin of the file
         *
         * @param filePath
         * @param stream
         * @return
         * @see {@link #writeFile(String, InputStream, boolean)}
         */
        public static boolean writeFile(String filePath, InputStream stream) {
            return writeFile(filePath, stream, false);
        }

        /**
         * write file
         *
         * @param stream the input stream
         * @param append if <code>true</code>, then bytes will be written to the end of the file rather than the beginning
         * @return return true
         * @throws RuntimeException if an error occurs while operator FileOutputStream
         */
        public static boolean writeFile(String filePath, InputStream stream, boolean append) {
            return writeFile(filePath != null ? new java.io.File(filePath) : null, stream, append);
        }

        /**
         * write file, the bytes will be written to the begin of the file
         *
         * @param file
         * @param stream
         * @return
         * @see {@link #writeFile(java.io.File, InputStream, boolean)}
         */
        public static boolean writeFile(java.io.File file, InputStream stream) {
            return writeFile(file, stream, false);
        }

        /**
         * write file
         *
         * @param file   the file to be opened for writing.
         * @param stream the input stream
         * @param append if <code>true</code>, then bytes will be written to the end of the file rather than the beginning
         * @return return true
         * @throws RuntimeException if an error occurs while operator FileOutputStream
         */
        public static boolean writeFile(java.io.File file, InputStream stream, boolean append) {
            OutputStream o = null;
            try {
                makeDirs(file.getAbsolutePath());
                o = new FileOutputStream(file, append);
                byte data[] = new byte[1024];
                int length = -1;
                while ((length = stream.read(data)) != -1) {
                    o.write(data, 0, length);
                }
                o.flush();
                return true;
            } catch (FileNotFoundException e) {
                throw new RuntimeException("FileNotFoundException occurred. ", e);
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                IO.close(o);
                IO.close(stream);
            }
        }

        /**
         * move file
         *
         * @param sourceFilePath
         * @param destFilePath
         */
        public static void moveFile(String sourceFilePath, String destFilePath) {
            if (TextUtils.isEmpty(sourceFilePath) || TextUtils.isEmpty(destFilePath)) {
                throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
            }
            moveFile(new java.io.File(sourceFilePath), new java.io.File(destFilePath));
        }

        /**
         * move file
         *
         * @param srcFile
         * @param destFile
         */
        public static void moveFile(java.io.File srcFile, java.io.File destFile) {
            boolean rename = srcFile.renameTo(destFile);
            if (!rename) {
                copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
                deleteFile(srcFile.getAbsolutePath());
            }
        }

        /**
         * copy file
         *
         * @param sourceFilePath
         * @param destFilePath
         * @return
         * @throws RuntimeException if an error occurs while operator FileOutputStream
         */
        public static boolean copyFile(String sourceFilePath, String destFilePath) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(sourceFilePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("FileNotFoundException occurred. ", e);
            }
            return writeFile(destFilePath, inputStream);
        }

        /**
         * read file to string list, a element of list is a line
         *
         * @param filePath
         * @param charsetName The name of a supported {@link java.nio.charset.Charset </code>charset<code>}
         * @return if file not exist, return null, else return content of file
         * @throws RuntimeException if an error occurs while operator BufferedReader
         */
        public static List<String> readFileToList(String filePath, String charsetName) {
            java.io.File file = new java.io.File(filePath);
            List<String> fileContent = new ArrayList<String>();
            if (file == null || !file.isFile()) {
                return null;
            }

            BufferedReader reader = null;
            try {
                InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
                reader = new BufferedReader(is);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    fileContent.add(line);
                }
                return fileContent;
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                IO.close(reader);
            }
        }

        /**
         * get file name from path, not include suffix
         * <p/>
         * <pre>
         *      getFileNameWithoutExtension(null)               =   null
         *      getFileNameWithoutExtension("")                 =   ""
         *      getFileNameWithoutExtension("   ")              =   "   "
         *      getFileNameWithoutExtension("abc")              =   "abc"
         *      getFileNameWithoutExtension("a.mp3")            =   "a"
         *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
         *      getFileNameWithoutExtension("c:\\")              =   ""
         *      getFileNameWithoutExtension("c:\\a")             =   "a"
         *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
         *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
         *      getFileNameWithoutExtension("/home/admin")      =   "admin"
         *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
         * </pre>
         *
         * @param filePath
         * @return file name from path, not include suffix
         * @see
         */
        public static String getFileNameWithoutExtension(String filePath) {
            if (TextUtils.isEmpty(filePath)) {
                return filePath;
            }

            int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
            int filePosi = filePath.lastIndexOf(java.io.File.separator);
            if (filePosi == -1) {
                return (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi));
            }
            if (extenPosi == -1) {
                return filePath.substring(filePosi + 1);
            }
            return (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1));
        }

        /**
         * get file name from path, include suffix
         * <p/>
         * <pre>
         *      getFileName(null)               =   null
         *      getFileName("")                 =   ""
         *      getFileName("   ")              =   "   "
         *      getFileName("a.mp3")            =   "a.mp3"
         *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
         *      getFileName("abc")              =   "abc"
         *      getFileName("c:\\")              =   ""
         *      getFileName("c:\\a")             =   "a"
         *      getFileName("c:\\a.b")           =   "a.b"
         *      getFileName("c:a.txt\\a")        =   "a"
         *      getFileName("/home/admin")      =   "admin"
         *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
         * </pre>
         *
         * @param filePath
         * @return file name from path, include suffix
         */
        public static String getFileName(String filePath) {
            if (TextUtils.isEmpty(filePath)) {
                return filePath;
            }

            int filePosi = filePath.lastIndexOf(java.io.File.separator);
            return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
        }

        /**
         * get folder name from path
         * <p/>
         * <pre>
         *      getFolderName(null)               =   null
         *      getFolderName("")                 =   ""
         *      getFolderName("   ")              =   ""
         *      getFolderName("a.mp3")            =   ""
         *      getFolderName("a.b.rmvb")         =   ""
         *      getFolderName("abc")              =   ""
         *      getFolderName("c:\\")              =   "c:"
         *      getFolderName("c:\\a")             =   "c:"
         *      getFolderName("c:\\a.b")           =   "c:"
         *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
         *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
         *      getFolderName("/home/admin")      =   "/home"
         *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
         * </pre>
         *
         * @param filePath
         * @return
         */
        public static String getFolderName(String filePath) {

            if (TextUtils.isEmpty(filePath)) {
                return filePath;
            }

            int filePosi = filePath.lastIndexOf(java.io.File.separator);
            return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
        }

        /**
         * get suffix of file from path
         * <p/>
         * <pre>
         *      getFileExtension(null)               =   ""
         *      getFileExtension("")                 =   ""
         *      getFileExtension("   ")              =   "   "
         *      getFileExtension("a.mp3")            =   "mp3"
         *      getFileExtension("a.b.rmvb")         =   "rmvb"
         *      getFileExtension("abc")              =   ""
         *      getFileExtension("c:\\")              =   ""
         *      getFileExtension("c:\\a")             =   ""
         *      getFileExtension("c:\\a.b")           =   "b"
         *      getFileExtension("c:a.txt\\a")        =   ""
         *      getFileExtension("/home/admin")      =   ""
         *      getFileExtension("/home/admin/a.txt/b")  =   ""
         *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
         * </pre>
         *
         * @param filePath
         * @return
         */
        public static String getFileExtension(String filePath) {
            if (TextUtils.isEmpty(filePath)) {
                return filePath;
            }

            int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
            int filePosi = filePath.lastIndexOf(java.io.File.separator);
            if (extenPosi == -1) {
                return "";
            }
            return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
        }

        /**
         * Creates the directory named by the trailing filename of this file, including the complete directory path required
         * to create this directory. <br/>
         * <br/>
         * <ul>
         * <strong>Attentions:</strong>
         * <li>makeDirs("C:\\Users\\Trinea") can only create users folder</li>
         * <li>makeFolder("C:\\Users\\Trinea\\") can create Trinea folder</li>
         * </ul>
         *
         * @param filePath
         * @return true if the necessary directories have been created or the target directory already exists, false one of
         * the directories can not be created.
         * <ul>
         * <li>if {@link File#getFolderName(String)} return null, return false</li>
         * <li>if target directory already exists, return true</li>
         * </ul>
         */
        public static boolean makeDirs(String filePath) {
            String folderName = getFolderName(filePath);
            if (TextUtils.isEmpty(folderName)) {
                return false;
            }

            java.io.File folder = new java.io.File(folderName);
            return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
        }

        /**
         * @param filePath
         * @return
         * @see #makeDirs(String)
         */
        public static boolean makeFolders(String filePath) {
            return makeDirs(filePath);
        }

        /**
         * Indicates if this file represents a file on the underlying file system.
         *
         * @param filePath
         * @return
         */
        public static boolean isFileExist(String filePath) {
            if (TextUtils.isEmpty(filePath)) {
                return false;
            }

            java.io.File file = new java.io.File(filePath);
            return (file.exists() && file.isFile());
        }

        /**
         * Indicates if this file represents a directory on the underlying file system.
         *
         * @param directoryPath
         * @return
         */
        public static boolean isFolderExist(String directoryPath) {
            if (TextUtils.isEmpty(directoryPath)) {
                return false;
            }

            java.io.File dire = new java.io.File(directoryPath);
            return (dire.exists() && dire.isDirectory());
        }

        /**
         * delete file or directory
         * <ul>
         * <li>if path is null or empty, return true</li>
         * <li>if path not exist, return true</li>
         * <li>if path exist, delete recursion. return true</li>
         * <ul>
         *
         * @param path
         * @return
         */
        public static boolean deleteFile(String path) {
            if (TextUtils.isEmpty(path)) {
                return true;
            }

            java.io.File file = new java.io.File(path);
            if (!file.exists()) {
                return true;
            }
            if (file.isFile()) {
                return file.delete();
            }
            if (!file.isDirectory()) {
                return false;
            }
            for (java.io.File f : file.listFiles()) {
                if (f.isFile()) {
                    f.delete();
                } else if (f.isDirectory()) {
                    deleteFile(f.getAbsolutePath());
                }
            }
            return file.delete();
        }

        /**
         * get file size
         * <ul>
         * <li>if path is null or empty, return -1</li>
         * <li>if path exist and it is a file, return file size, else return -1</li>
         * <ul>
         *
         * @param path
         * @return returns the length of this file in bytes. returns -1 if the file does not exist.
         */
        public static long getFileSize(String path) {
            if (TextUtils.isEmpty(path)) {
                return -1;
            }

            java.io.File file = new java.io.File(path);
            return (file.exists() && file.isFile() ? file.length() : -1);
        }

        public interface SaveResultCallback {
            void onSavedSuccess(String savedPath);

            void onSavedFailed();
        }

    }

    public static class IO {
        /**
         * 关闭流
         *
         * @param closeable
         */
        public static void close(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    public static class Date {
        private static SimpleDateFormat m = new SimpleDateFormat("MM", Locale.getDefault());
        private static SimpleDateFormat hH = new SimpleDateFormat("HH", Locale.getDefault());
        private static SimpleDateFormat hh = new SimpleDateFormat("hh", Locale.getDefault());
        private static SimpleDateFormat d = new SimpleDateFormat("dd", Locale.getDefault());
        private static SimpleDateFormat md = new SimpleDateFormat("MM-dd", Locale.getDefault());
        private static SimpleDateFormat mdc = new SimpleDateFormat("MM月dd日", Locale.getDefault());
        private static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        private static SimpleDateFormat ymdDot = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        private static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        private static SimpleDateFormat ymdhmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        private static SimpleDateFormat ymdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        private static SimpleDateFormat hm = new SimpleDateFormat("HH:mm", Locale.getDefault());
        private static SimpleDateFormat mdhm = new SimpleDateFormat("MM月dd日 HH:mm", Locale.getDefault());
        private static SimpleDateFormat mdhmLink = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());

        /**
         * 年月日[2015-07-28]
         *
         * @param timeInMills
         * @return
         */
        public static String getYmd(long timeInMills) {
            return ymd.format(new java.util.Date(timeInMills));
        }

        /**
         * 年月日[2015.07.28]
         *
         * @param timeInMills
         * @return
         */
        public static String getYmdDot(long timeInMills) {
            return ymdDot.format(new java.util.Date(timeInMills));
        }

        public static String getYmdhms(long timeInMills) {
            return ymdhms.format(new java.util.Date(timeInMills));
        }

        public static String getYmdhmsS(long timeInMills) {
            return ymdhmss.format(new java.util.Date(timeInMills));
        }

        public static String getYmdhm(long timeInMills) {
            return ymdhm.format(new java.util.Date(timeInMills));
        }

        public static String getHm(long timeInMills) {
            return hm.format(new java.util.Date(timeInMills));
        }

        public static String getMd(long timeInMills) {
            return md.format(new java.util.Date(timeInMills));
        }

        public static String getMdc(long timeInMills) {
            return mdc.format(new java.util.Date(timeInMills));
        }

        public static String getMdhm(long timeInMills) {
            return mdhm.format(new java.util.Date(timeInMills));
        }

        public static String getMdhmLink(long timeInMills) {
            return mdhmLink.format(new java.util.Date(timeInMills));
        }

        public static String getM(long timeInMills) {
            return m.format(new java.util.Date(timeInMills));
        }

        public static String getD(long timeInMills) {
            return d.format(new java.util.Date(timeInMills));
        }

        public static boolean isNight(long timeInMills) {
            String hour = hH.format(new java.util.Date(timeInMills));
            int k = Integer.parseInt(hour);
            //晚上
//白天
            return (k >= 0 && k < 7) || (k >= 19 && k < 24);
        }

        /**
         * 是否是今天
         *
         * @param timeInMills
         * @return
         */
        public static boolean isToday(long timeInMills) {
            String dest = getYmd(timeInMills);
            String now = getYmd(Calendar.getInstance().getTimeInMillis());
            return dest.equals(now);
        }

        /**
         * 是否是同一天
         *
         * @param aMills
         * @param bMills
         * @return
         */
        public static boolean isSameDay(long aMills, long bMills) {
            String aDay = getYmd(aMills);
            String bDay = getYmd(bMills);
            return aDay.equals(bDay);
        }

        public static String getSayHello(long timeMillis) {
            java.util.Date d = new java.util.Date(timeMillis);
            int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hours > 6 && hours <= 11) {
                return "早上好";
            } else if (hours > 11 && hours <= 13) {
                return "中午好";
            } else if (hours > 13 && hours <= 18) {
                return "下午好";
            } else if (hours > 18 && hours <= 19) {
                return "傍晚好";
            } else if (hours > 19 && hours <= 23) {
                return "晚上好";
            } else if (hours >= 0 && hours <= 6) {
                return "凌晨好";
            } else {
                return "您好";
            }
        }

        /**
         * 计算两个日期之间相差的天数
         *
         * @param smdate 较小的时间
         * @param bdate  较大的时间
         * @return 相差天数
         */
        public static int daysBetween(java.util.Date smdate, java.util.Date bdate) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(betweenDays));
        }

        /**
         * 计算两个日期之间相差的天数
         *
         * @param timeInMillisB 较大的时间
         * @param timeInMillisS 较小的时间
         * @return 相差天数
         */
        public static int daysBetween(long timeInMillisB, long timeInMillisS) {
            return (int) ((timeInMillisB - timeInMillisS) / (1000 * 3600 * 24));
        }

        /**
         * 获取年份
         *
         * @param mills
         * @return
         */
        public static int getYear(long mills) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);
            return calendar.get(Calendar.YEAR);
        }

        /**
         * 获取月份
         *
         * @param mills
         * @return
         */
        public static int getMonth(long mills) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);
            return calendar.get(Calendar.MONTH) + 1;
        }


        /**
         * 获取月份的天数
         *
         * @param mills
         * @return
         */
        public static int getDaysInMonth(long mills) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);

            switch (month) {
                case Calendar.JANUARY:
                case Calendar.MARCH:
                case Calendar.MAY:
                case Calendar.JULY:
                case Calendar.AUGUST:
                case Calendar.OCTOBER:
                case Calendar.DECEMBER:
                    return 31;
                case Calendar.APRIL:
                case Calendar.JUNE:
                case Calendar.SEPTEMBER:
                case Calendar.NOVEMBER:
                    return 30;
                case Calendar.FEBRUARY:
                    return (year % 4 == 0) ? 29 : 28;
                default:
                    throw new IllegalArgumentException("Invalid Month");
            }
        }


        /**
         * 获取星期,0-周日,1-周一，2-周二，3-周三，4-周四，5-周五，6-周六
         *
         * @param mills
         * @return
         */
        public static int getWeek(long mills) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);

            return calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }

        public static String getWeek(long mills, String unit) {
            switch (getWeek(mills)) {
                case 0:
                    return unit + "日";
                case 1:
                    return unit + "一";
                case 2:
                    return unit + "二";
                case 3:
                    return unit + "三";
                case 4:
                    return unit + "四";
                case 5:
                    return unit + "五";
                case 6:
                    return unit + "六";
                default:
                    return unit + "一";
            }
        }

        /**
         * 日期格式字符串转换成时间戳
         *
         * @param dateStr 字符串日期
         * @param format  如：yyyy-MM-dd HH:mm:ss
         * @return
         */
        public static long date2TimeStamp(String dateStr, String format) {
            try {
                DateFormat df = new SimpleDateFormat(format);
                java.util.Date date = df.parse(dateStr);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                long tim = cal.getTimeInMillis();
                return tim;
            } catch (Exception e) {
                e.printStackTrace();
                return 0L;
            }
        }

        /**
         * 获取当月第一天的时间（毫秒值）
         *
         * @param mills
         * @return
         */
        public static long getFirstOfMonth(long mills) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            return calendar.getTimeInMillis();
        }

    }


    public static class Arith {
        //默认除法运算精度
        private static final int DEF_DIV_SCALE = 10;

        //加
        public static double add(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.add(b2).doubleValue();
        }

        //减
        public static double sub(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.subtract(b2).doubleValue();
        }

        //乘
        public static double mul(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.multiply(b2).doubleValue();
        }

        //除
        public static double div(double v1, double v2) {
            return div(v1, v2, DEF_DIV_SCALE);
        }


        public static double div(double v1, double v2, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        public static float divf(float v1, float v2) {
            return divf(v1, v2, DEF_DIV_SCALE);
        }

        //除->保留小数位
        public static float divf(float v1, float v2, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            BigDecimal b1 = new BigDecimal(Float.toString(v1));
            BigDecimal b2 = new BigDecimal(Float.toString(v2));
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
        }  //除->保留小数位

        //取小数位
        public static double round(double v, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        public static float parseFloat(String v1) {
            BigDecimal b1 = new BigDecimal(v1);
            return b1.floatValue();
        }

        public static double parseDouble(String v1) {
            BigDecimal b1 = new BigDecimal(v1);
            return b1.doubleValue();
        }
    }

    public static class Empty {


        public static boolean check(Object obj) {
            return obj == null;
        }

        public static boolean check(List list) {
            return list == null || list.isEmpty();
        }

        public static boolean check(Object[] array) {
            return array == null || array.length == 0;
        }

        public static boolean check(String str) {
            return str == null || "".equals(str);
        }

        public static boolean check(Map map) {
            return map == null || map.isEmpty();
        }

        public static boolean check(Set set) {
            return set == null || set.isEmpty();
        }


    }


}
