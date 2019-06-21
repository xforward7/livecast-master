package com.aidingyun.ynlive.app.utils;

import android.content.Context;


import java.util.HashMap;

/**
 * <pre>
 *     project name: androidclient
 *     author      : 翁嘉若
 *     create time : 2017/6/15 0015 11:35
 *
 * </pre>
 */
public class RequestParam {
    private String mUserAgent = "";     //用户Id
    private String mCookie = "";       //登录ticket
    private String mDeviceId = "";     //设备唯一标识号

    private RequestParam(Builder builder) {
        mUserAgent = builder.mUserAgent;
        mCookie = builder.mCookie;
        mDeviceId = builder.mDeviceId;
    }

    public HashMap<String, String> createOptions() {
        HashMap<String, String> commonOption = new HashMap<>();
        commonOption.put("Cookie", mCookie);
        commonOption.put("User-Agent", mUserAgent);
        commonOption.put("DeviceId", mDeviceId);
        return commonOption;
    }

    public static final class Builder {
        private String mUserAgent = "";     //用户Id
        private String mCookie = "";       //登录ticket
        private String mDeviceId = "";     //设备唯一标识号

        public Builder() {
        }

        public Builder userAgent(String mUserAgent) {
            this.mUserAgent = mUserAgent;
            return this;
        }

        public Builder cookie(String mCookie) {
            this.mCookie = mCookie;
            return this;
        }

        public Builder deviceId(Context context) {
            mDeviceId = Installation.id(context);
            return this;
        }

        public RequestParam build() {
            return new RequestParam(this);
        }
    }
}
