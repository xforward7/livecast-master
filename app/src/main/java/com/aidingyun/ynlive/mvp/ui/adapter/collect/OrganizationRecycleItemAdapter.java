package com.aidingyun.ynlive.mvp.ui.adapter.collect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.mvp.model.entity.CollectOrganizationModel;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.ui.activity.organization.OrganizationDetailActivity;
import com.aidingyun.ynlive.mvp.ui.widget.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class OrganizationRecycleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CollectOrganizationModel.OrganizationInfo> results;

    public OrganizationRecycleItemAdapter(Context context, List<CollectOrganizationModel.OrganizationInfo> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_organization_item, parent, false));
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

        LoadImage.loadNormalImage(context,results.get(position).getOrganization_icon(),holder.circle_head);
        holder.tv_lable.setText(results.get(position).getName());
        holder.tv_grade.setText("0门课程");
        holder.tv_collent.setText(results.get(position).getCollection());
        holder.tv_recommend_learning.setText("暂无推荐");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context,OrganizationDetailActivity.class));
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView circle_head;
        public TextView tv_lable;
        public TextView tv_grade;
        public TextView tv_collent;
        public TextView tv_recommend_learning;

        public ItemViewHolder(View itemView) {
            super(itemView);
            circle_head = (CircleImageView) itemView.findViewById(R.id.circle_head);
            tv_lable = (TextView) itemView.findViewById(R.id.tv_lable);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
            tv_collent = (TextView) itemView.findViewById(R.id.tv_collent);
            tv_recommend_learning = (TextView) itemView.findViewById(R.id.tv_recommend_learning);
        }
    }
}
