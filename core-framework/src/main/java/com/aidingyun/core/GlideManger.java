package com.aidingyun.core;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;




public class GlideManger {

    public static GlideManger getInstance() {
        return GlideMangerHolder.instance;
    }

    private static class GlideMangerHolder {
        static GlideManger instance = new GlideManger();
    }

    public RequestOptions userPicOptions = RequestOptions
            .circleCropTransform()
            .placeholder(R.drawable.avatar_default)
            .error(R.drawable.avatar_default)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    public RequestOptions imgOptions = RequestOptions
            .placeholderOf(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

}
