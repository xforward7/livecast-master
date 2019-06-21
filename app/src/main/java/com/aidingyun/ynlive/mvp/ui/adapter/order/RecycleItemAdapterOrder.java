package com.aidingyun.ynlive.mvp.ui.adapter.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.mvp.model.entity.CourseOrderInfo;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemAdapterOrder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CourseOrderInfo.DataBean.ListBean> courseBeans;


    public RecycleItemAdapterOrder(Context context, List<CourseOrderInfo.DataBean.ListBean> courseBeans) {
        this.context = context;
        this.courseBeans = courseBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            bind((ItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return courseBeans.size();
    }


    private void bind(ItemViewHolder holder, int position){
//        Glide.with(context)
//                .load(results.get(position).getPhoto())
//                .into(holder.item_recyc_type3_item_img);
        LoadImage.loadNormalImage(context,courseBeans.get(position).getPhoto(),holder.item_recyc_type3_item_img);
        holder.tv_order_id.setText(courseBeans.get(position).getOrderid());
        holder.tv_lable.setText(courseBeans.get(position).getTitle());
//        holder.tv_grade.setText(courseBeans.get(position).getScore()+"分");
//        holder.tv_collent.setText(courseBeans.get(position).getCollection());
        holder.tv_money.setText(courseBeans.get(position).getConsume_fee());
        holder.tv_total_price.setText(courseBeans.get(position).getConsume_fee());
        if (courseBeans.get(position).getOrder_status().equals("0")){
            holder.tv_payment_status.setText("待付款");
            holder.tv_cancel_btn.setVisibility(View.VISIBLE);
        }else {
            holder.tv_payment_status.setText("已支付");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseDetailActivity.start(context,courseBeans.get(position).getGoods_id());
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_order_time;
        TextView tv_order_id;
        TextView tv_total_price;
        TextView tv_payment_status;
        TextView tv_comment_btn;
        TextView tv_cancel_btn;
        public ImageView item_recyc_type3_item_img;
        public TextView tv_lable;
        public TextView tv_grade;
        public TextView tv_collent;
        public TextView tv_money;
        public TextView tv_unit;


        public ItemViewHolder(View itemView) {
            super(itemView);
            item_recyc_type3_item_img = (ImageView) itemView.findViewById(R.id.item_recyc_type3_item_img);
            tv_lable = (TextView) itemView.findViewById(R.id.tv_lable);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
            tv_collent = (TextView) itemView.findViewById(R.id.tv_collent);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
            tv_unit = (TextView) itemView.findViewById(R.id.tv_unit);
            tv_order_time = (TextView) itemView.findViewById(R.id.tv_order_time);
            tv_order_id = (TextView) itemView.findViewById(R.id.tv_order_id);
            tv_total_price = (TextView) itemView.findViewById(R.id.tv_total_price);
            tv_payment_status = (TextView) itemView.findViewById(R.id.tv_payment_status);
            tv_comment_btn = (TextView) itemView.findViewById(R.id.tv_comment_btn);
            tv_cancel_btn = (TextView) itemView.findViewById(R.id.tv_cancel_btn);
        }
    }
}
