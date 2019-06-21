package com.aidingyun.core.cache;

import android.content.Context;
import android.text.TextUtils;

import com.jess.arms.utils.DataHelper;

/**
 * Created by wanglei on 2016/11/27.
 */

public class SharedPref {

    private Context context;//ApplicationContext
    private static SharedPref instance;

    private SharedPref(Context context) {
        this.context = context;
    }

    public static SharedPref getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPref.class) {
                if (instance == null) {
                    instance = new SharedPref(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void remove(String key) {
        context.getSharedPreferences(DataHelper.SP_NAME, Context.MODE_PRIVATE).edit().remove(key).apply();
    }

    public boolean contains(String key) {
        return context.getSharedPreferences(DataHelper.SP_NAME, Context.MODE_PRIVATE).contains(key);
    }

    public void clear() {
        context.getSharedPreferences(DataHelper.SP_NAME, Context.MODE_PRIVATE).edit().clear().apply();
    }

    public void putInt(String key, int value) {
        DataHelper.setIntergerSF(context, key, value);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return context.getSharedPreferences(DataHelper.SP_NAME, Context.MODE_PRIVATE).getInt(key, defValue);
    }

    public void putLong(String key, Long value) {
        context.getSharedPreferences(DataHelper.SP_NAME, Context.MODE_PRIVATE).edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return getLong(key, 0L);
    }

    public long getLong(String key, long defValue) {
        return context.getSharedPreferences(DataHelper.SP_NAME, Context.MODE_PRIVATE).getLong(key, defValue);
    }

    public void putBoolean(String key, Boolean value) {
        context.getSharedPreferences(DataHelper.SP_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return context.getSharedPreferences(DataHelper.SP_NAME, Context.MODE_PRIVATE).getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        DataHelper.setStringSF(context, key, value);
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defValue) {
        String value = DataHelper.getStringSF(context, key);
        return TextUtils.isEmpty(value) ? defValue : value;
    }

}
