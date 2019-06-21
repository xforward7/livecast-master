package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemBuyCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CourseDetailInfo.SectionBean> sectionBeans;
    private int selectItem = 0;
    boolean all = false;
    //这个是checkbox的Hashmap集合
    public static HashMap<Integer, Boolean> isSelected;
    public boolean getAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }


    public RecycleItemBuyCourseAdapter(Context context, List<CourseDetailInfo.SectionBean> sectionBeans) {
        this.context = context;
        this.sectionBeans = sectionBeans;
        isSelected = new HashMap<>();
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        RecycleItemBuyCourseAdapter.isSelected = isSelected;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_course_item, parent, false));
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
        holder.tv_no.setText("0"+position+1);
//        holder.tv_buy_status.setText(sectionBeans.get(position).getType());
        // 根据isSelected来设置checkbox的选中状况
        if (all){
            holder.checkbox_buy_item.setChecked(true);
        }
        holder.checkbox_buy_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isSelected.put(position, true);
                }else {
                    isSelected.remove(position);
                }
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_no;
        public TextView tv_course_title;
        public TextView tv_time;
        public TextView tv_buy_status;
        public CheckBox checkbox_buy_item;


        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            tv_course_title = (TextView) itemView.findViewById(R.id.tv_course_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_buy_status = (TextView) itemView.findViewById(R.id.tv_buy_status);
            checkbox_buy_item = (CheckBox) itemView.findViewById(R.id.checkbox_buy_item);
        }
    }
}
