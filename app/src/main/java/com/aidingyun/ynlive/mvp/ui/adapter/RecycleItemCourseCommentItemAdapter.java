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
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.ui.widget.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemCourseCommentItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CourseDetailInfo.SectionBean> sectionBeans;


    public RecycleItemCourseCommentItemAdapter(Context context, List<CourseDetailInfo.SectionBean> sectionBeans) {
        this.context = context;
        this.sectionBeans = sectionBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.course_comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            bind((ItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return sectionBeans.size();
    }


    private void bind(ItemViewHolder holder, int position){
//        LoadImage.loadCircleImage(context,sectionBeans.get(position).getIcon());
//        holder.tv_course_title.setText(sectionBeans.get(position).getSection_title()+"分");
//        holder.tv_time.setText(sectionBeans.get(position).getSection_date()+"  "+sectionBeans.get(position).getSection_time());
//        holder.tv_no.setText("0"+position+1);

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_nick_name;
        public TextView tv_user_id;
        public TextView tv_comment_time;
        public TextView tv_comment_content;
        public TextView tv_score;
        public CircleImageView iv_head;


        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_nick_name = (TextView) itemView.findViewById(R.id.tv_nick_name);
            tv_user_id = (TextView) itemView.findViewById(R.id.tv_user_id);
            tv_comment_time = (TextView) itemView.findViewById(R.id.tv_comment_time);
            tv_comment_content = (TextView) itemView.findViewById(R.id.tv_comment_content);
            tv_score = (TextView) itemView.findViewById(R.id.tv_score);
            iv_head = (CircleImageView) itemView.findViewById(R.id.iv_head);
        }
    }
}
