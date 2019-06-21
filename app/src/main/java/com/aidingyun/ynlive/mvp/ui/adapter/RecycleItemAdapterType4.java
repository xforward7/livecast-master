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
import com.aidingyun.ynlive.mvp.model.entity.CurriculumTypeModel;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.ui.widget.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;


import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemAdapterType4 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeCourseModel.HostDataBean.HostListBean> results;

    //get & set
    public List<HomeCourseModel.HostDataBean.HostListBean> getResults() {
        return results;
    }

    public RecycleItemAdapterType4(Context context, List<HomeCourseModel.HostDataBean.HostListBean> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type4_item, parent, false));
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

    /////////////////////////////

    private void bind(ItemViewHolder holder, int position){
//        Glide.with(context)
//                .load(results.get(position).getPhoto())
//                .into(holder.circle_head);
        LoadImage.loadCircleImage(context,results.get(position).getPhoto(),holder.circle_head);
        holder.tv_lable.setText(results.get(position).getReal_name());
        holder.tv_technical.setText(results.get(position).getProfessional());
        holder.tv_collent.setText(results.get(position).getFans()+"人收藏");
        holder.tv_grade.setText(results.get(position).getGroom_count()+"门课程");
        holder.tv_recommend_learning.setText(results.get(position).getGroom_title());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView circle_head;
        public TextView tv_lable;
        public TextView tv_technical;
        public TextView tv_grade;//课程数量
        public TextView tv_collent;
        public TextView tv_recommend_learning;

        public ItemViewHolder(View itemView) {
            super(itemView);
            circle_head = (CircleImageView) itemView.findViewById(R.id.circle_head);
            tv_lable = (TextView) itemView.findViewById(R.id.tv_lable);
            tv_technical = (TextView) itemView.findViewById(R.id.tv_technical);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
            tv_collent = (TextView) itemView.findViewById(R.id.tv_collent);
            tv_recommend_learning = (TextView) itemView.findViewById(R.id.tv_recommend_learning);
        }
    }
}
