package com.aidingyun.ynlive.component.converter;

import android.support.annotation.NonNull;

import com.aidingyun.ynlive.component.exception.ApiException;
import com.aidingyun.ynlive.mvp.model.entity.base.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by 翁嘉若 on 2017/3/6.
 */

public class CheckGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson mGson;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public CheckGsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.mGson = new Gson();
        this.adapter = adapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String response = value.string();
        BaseResponse result = mGson.fromJson(response, BaseResponse.class);
        int ec = result.getEc();
//        if (ec == -3001) {
//            LoginManager.getInstance().onLogout(GlobalContext.getAppContext(), new AsyncCallback<Boolean>() {//全局处理下线问题
//                @Override
//                public void onSuccess(Boolean aBoolean) {
//                    if (GlobalUtils.getAccountCount(GlobalContext.getAppContext()) != 0) {
//                        AccountHistoryActivity.start(GlobalContext.getAppContext(), Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    } else {
//                        LoginActivity.start(GlobalContext.getAppContext(), Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    }
//                }
//
//                @Override
//                public void onFailed(Throwable t) {
//
//                }
//            });
//        } else
        if (ec != 0) {
            value.close();
            throw new ApiException(ec, result.getEm());
        }
        MediaType type = value.contentType();
        Charset charset = type != null ? type.charset(UTF_8) : UTF_8;
        InputStream is = new ByteArrayInputStream(response.getBytes());
        InputStreamReader reader = new InputStreamReader(is, charset);
        JsonReader jsonReader = mGson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
