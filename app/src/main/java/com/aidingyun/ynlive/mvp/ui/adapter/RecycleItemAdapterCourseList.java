package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.ui.widget.DefinitionController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemAdapterCourseList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private List<CourseDetailInfo.SectionBean> sectionBeans;
    private MyItemClickListener mItemClickListener;

    public RecycleItemAdapterCourseList(Context context, List<CourseDetailInfo.SectionBean> sectionBeans) {
        this.context = context;
        this.sectionBeans = sectionBeans;
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent,false);
        ItemViewHolder viewHolder = new ItemViewHolder(itemView,mItemClickListener);
        return viewHolder;
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
        holder.tv_course_title.setText(sectionBeans.get(position).getSection_title()+"分");
        holder.tv_time.setText(sectionBeans.get(position).getSection_date()+"  "+sectionBeans.get(position).getSection_time());
        holder.tv_no.setText("0"+(position+1));
        if (sectionBeans.get(position).getAllow().equals("0")){
            holder.iv_lock.setVisibility(View.GONE);
        }else if (sectionBeans.get(position).getAllow().equals("1")){
            holder.iv_lock.setVisibility(View.VISIBLE);
        }else if (sectionBeans.get(position).getAllow().equals("2")){
            holder.iv_lock.setVisibility(View.GONE);
            holder.tv_replay_btn.setVisibility(View.VISIBLE);
        }



    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_no;
        public TextView tv_course_title;
        public TextView tv_time;
        public TextView tv_replay_btn;//回看按钮
        public ImageView iv_lock;
        private MyItemClickListener mItemClickListener;

        public ItemViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            tv_course_title = (TextView) itemView.findViewById(R.id.tv_course_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_replay_btn = (TextView) itemView.findViewById(R.id.tv_replay_btn);
            iv_lock = (ImageView) itemView.findViewById(R.id.iv_lock);
            this.mItemClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(v,getPosition());
            }
        }
    }

    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }


}
