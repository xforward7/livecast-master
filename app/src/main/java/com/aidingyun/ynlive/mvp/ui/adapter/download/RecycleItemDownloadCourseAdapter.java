package com.aidingyun.ynlive.mvp.ui.adapter.download;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemDownloadCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CourseDetailInfo.SectionBean> sectionBeans;
    private int selectItem = 0;
    boolean all = false;
    //这个是checkbox的Hashmap集合
    public HashMap<String, Boolean> isSelected;
    public boolean getAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public HashMap<String, Boolean> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(HashMap<String, Boolean> isSelected) {
        this.isSelected = isSelected;
    }

    public RecycleItemDownloadCourseAdapter(Context context, List<CourseDetailInfo.SectionBean> sectionBeans) {
        this.context = context;
        this.sectionBeans = sectionBeans;
        isSelected = new HashMap<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_download_course, parent, false));
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
        holder.tv_course_title.setText(sectionBeans.get(position).getSection_title());
        // 根据isSelected来设置checkbox的选中状况
        if (all){
            holder.checkbox_download_course.setChecked(true);
            isSelected.put(sectionBeans.get(position).getSectionid(), true);
        }else {
            holder.checkbox_download_course.setChecked(false);
            isSelected.remove(sectionBeans.get(position).getSectionid());
        }
        holder.checkbox_download_course.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isSelected.put(sectionBeans.get(position).getSectionid(), true);
                }else {
                    isSelected.remove(sectionBeans.get(position).getSectionid());
                }
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_download_status;
        public TextView tv_course_title;
        public CheckBox checkbox_download_course;


        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_course_title = (TextView) itemView.findViewById(R.id.tv_course_title);
            tv_download_status = (TextView) itemView.findViewById(R.id.tv_download_status);
            checkbox_download_course = (CheckBox) itemView.findViewById(R.id.checkbox_download_course);
        }
    }
}
