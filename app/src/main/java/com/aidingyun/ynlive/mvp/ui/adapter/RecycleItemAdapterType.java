package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.model.entity.SearchCourseModel;
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;
import com.bumptech.glide.Glide;
import com.facebook.stetho.common.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemAdapterType extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeCourseModel.TypeDataBean.TypeCourseDataBean> results;


    public RecycleItemAdapterType(Context context, List<HomeCourseModel.TypeDataBean.TypeCourseDataBean> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type3_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            bind((ItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    private void bind(ItemViewHolder holder, int position){
//        Glide.with(context)
//                .load(results.get(position).getPhoto())
//                .into(holder.item_recyc_type3_item_img);
        LoadImage.loadNormalImage(context,results.get(position).getPhoto(),holder.item_recyc_type3_item_img);
        holder.tv_lable.setText(results.get(position).getTitle());
        holder.tv_grade.setText(" "+String.format("%.1f", Float.parseFloat(results.get(position).getScore())));
        holder.tv_collent.setText(results.get(position).getCollection()+"人收藏");

        if (results.get(position).getType().equals("0")) { // 直播课
            if (results.get(position).getLive_status().equals("0")) {
                // 未直播
                holder.tv_live_status.setImageResource(R.mipmap.icon_coursetype_live);
            } else  {
                // 直播中
                holder.tv_live_status.setImageResource(R.mipmap.icon_course_living);
            }
        } else  { // 录播课
            holder.tv_live_status.setImageResource(R.mipmap.icon_coursetype_record);
        }

        if (results.get(position).getAllow().equals("0")){
            holder.tv_money.setText("免费");
            holder.tv_unit.setVisibility(View.INVISIBLE);
        }else if (results.get(position).getAllow().equals("1")){
            holder.tv_money.setText("加密");
            holder.tv_unit.setVisibility(View.INVISIBLE);
        }else if (results.get(position).getAllow().equals("2")){
            holder.tv_money.setText(results.get(position).getPrice());
            holder.tv_unit.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseDetailActivity.start(context,results.get(position).getCourseid());
//                ToastUtils.show(holder.mImageView.getContext(), "" + position);
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_recyc_type3_item_img;
        public TextView tv_lable;
        public TextView tv_grade;
        public TextView tv_collent;
        public TextView tv_money;
        public TextView tv_unit;
        ImageView tv_live_status;

        public ItemViewHolder(View itemView) {
            super(itemView);
            item_recyc_type3_item_img = (ImageView) itemView.findViewById(R.id.item_recyc_type3_item_img);
            tv_lable = (TextView) itemView.findViewById(R.id.tv_lable);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
            tv_collent = (TextView) itemView.findViewById(R.id.tv_collent);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
            tv_unit = (TextView) itemView.findViewById(R.id.tv_unit);
            tv_live_status = (ImageView) itemView.findViewById(R.id.tv_live_status);
        }
    }
}
