package com.aidingyun.ynlive.app.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.YeNeiApplicaiton;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.jess.arms.http.imageloader.glide.GlideOptions.bitmapTransform;


/**
 * Created by Andy on 2016/11/8.
 */

public class LoadImage {
    //加载展示圆形图片
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        Uri parseUrl = Uri.parse("http:"+url);
//        Glide.with(context)
//                .load(parseUrl)
//                .transition(withCrossFade())
//                .into(imageView);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_loading);
        Glide.with(context)
                .load(parseUrl)
                .apply(options)
                .apply(bitmapTransform(new CropCircleTransformation()))
                .into(imageView);

//        Picasso.with(context)
//                .load(parseUrl)
//                .placeholder(R.drawable.avatar_default)//指定图片未加载成功前显示的图片
//                .error(R.drawable.avatar_default)//指定图片加载失败时显示的图片
//                .transform(new CircleTransform())//指定图片的形状为圆形
//                .into(imageView);//指定图片展示的控件
    }

    public static void loadCircleImagedefult(Context context, int url, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.avatar_default)//指定图片未加载成功前显示的图片
//                .error(R.drawable.icon_loading)//指定图片加载失败时显示的图片
                .transform(new CircleTransform())//指定图片的形状为圆形
                .into(imageView);//指定图片展示的控件
    }

    //加载展示普通图片
    public static void loadNormalImage(Context context, String url, ImageView imageView) {
        Uri parseUrl = Uri.parse("http:"+url);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_loading);
        Glide.with(context)
                .load(parseUrl)
                .apply(options)
                .transition(withCrossFade())
                .into(imageView);
//        Uri parseUrl = Uri.parse("http:"+url);
//        Picasso.with(context)
//                .load(parseUrl)
//                .placeholder(R.drawable.icon_loading)//指定图片未加载成功前显示的图片
////                .error(R.drawable.not_showed)//指定图片加载失败时显示的图片
//                .into(imageView);//指定图片展示的控件
    }
}
