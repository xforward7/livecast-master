package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;
import com.aidingyun.ynlive.mvp.ui.widget.gallery.CardAdapterHelper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jameson.io.library.util.ToastUtils;

/**
 * Created by jameson on 8/30/16.
 */
class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    Context context;
    private List<HomeCourseModel.ChoiceCourseDataBean> mList = new ArrayList<>();
    int size = 0;
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();

    public CardAdapter(Context context,List<HomeCourseModel.ChoiceCourseDataBean> mList,int size) {
        this.context = context;
        this.mList = mList;
        this.size = size;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_item, parent, false);
//        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
//        holder.mImageView.setImageResource(mList.get(position));
//        Glide.with(context)
//                .load(mList.get(position).getPhoto())
//                .into(holder.mImageView);
        LoadImage.loadNormalImage(context,mList.get(position).getPhoto(),holder.mImageView);
        holder.tv_title.setText(mList.get(position).getTitle());
        holder.tv_start.setText(" "+String.format("%.1f", Float.parseFloat(mList.get(position).getScore())));
        holder.tv_collent.setText(mList.get(position).getCollection()+"人收藏");

        if (mList.get(position).getType().equals("0")) { // 直播课
            if (mList.get(position).getLive_status().equals("0")) {
                // 未直播
                holder.tv_live_status.setImageResource(R.mipmap.icon_coursetype_live);
            } else  {
                // 直播中
                holder.tv_live_status.setImageResource(R.mipmap.icon_course_living);
            }
        } else  { // 录播课
            holder.tv_live_status.setImageResource(R.mipmap.icon_coursetype_record);
        }

        if (mList.get(position).getAllow().equals("0")){
            holder.tv_unit.setVisibility(View.GONE);
            holder.tv_price.setText("免费");
        }else if (mList.get(position).getAllow().equals("1")){
            holder.tv_unit.setVisibility(View.GONE);
            holder.tv_price.setText("加密");
        }else if (mList.get(position).getAllow().equals("2")){
            holder.tv_price.setText(mList.get(position).getPrice());
        }

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseDetailActivity.start(context,mList.get(position).getCourseid());
//                ToastUtils.show(holder.mImageView.getContext(), "" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  ImageView mImageView;
        public  TextView tv_title;
        public  TextView tv_start;
        public  TextView tv_collent;
        public  TextView tv_price;
        public  TextView tv_unit;
        public ImageView tv_live_status;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_start = (TextView) itemView.findViewById(R.id.tv_start);
            tv_collent = (TextView) itemView.findViewById(R.id.tv_collent);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_unit = (TextView) itemView.findViewById(R.id.tv_unit);
            tv_live_status = (ImageView) itemView.findViewById(R.id.tv_live_status);
        }

    }

}
