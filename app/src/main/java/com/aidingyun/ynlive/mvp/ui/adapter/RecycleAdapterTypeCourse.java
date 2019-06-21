package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.FullyGridLayoutManager;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.model.entity.SearchCourseModel;
import com.aidingyun.ynlive.mvp.ui.activity.search.SearchCourseActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleAdapterTypeCourse extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeCourseModel.TypeDataBean> typeDataBeans;

    //get & set

    public RecycleAdapterTypeCourse(Context context, List<HomeCourseModel.TypeDataBean> typeDataBeans) {
        this.context = context;
        this.typeDataBeans = typeDataBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type3, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            bind((ItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return typeDataBeans.size();
    }

    private void bind(ItemViewHolder holder, int position){
        holder.tv_type_name.setText(typeDataBeans.get(position).getType_name());
//        Glide.with(context)
//                .load(typeDataBeans.get(position).getApp_top_pic())
//                .into(holder.top_image);
        LoadImage.loadNormalImage(context,typeDataBeans.get(position).getApp_top_pic(),holder.top_image);
        holder.item_recyc_type3.setLayoutManager(new FullyGridLayoutManager(holder.item_recyc_type3.getContext(), 1, GridLayoutManager.VERTICAL, false));
        holder.item_recyc_type3.setAdapter(new RecycleItemAdapterType(context, typeDataBeans.get(position).getType_course_data()));
        holder.item_recyc_type3.setNestedScrollingEnabled(false);

        holder.tv_check_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 跳转到更多
                 */
                SearchCourseActivity.start(context,typeDataBeans.get(position).getType_name());
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView top_image;
        public TextView tv_type_name;
        public RecyclerView item_recyc_type3;
        public LinearLayout title_linear;
        public TextView tv_check_more;

        public ItemViewHolder(View itemView) {
            super(itemView);
            item_recyc_type3 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type3);
            top_image = (ImageView) itemView.findViewById(R.id.top_image);
            tv_type_name = (TextView) itemView.findViewById(R.id.tv_type_name);
            title_linear = (LinearLayout) itemView.findViewById(R.id.title_linear);
            tv_check_more = (TextView) itemView.findViewById(R.id.tv_check_more);
        }
    }
}
