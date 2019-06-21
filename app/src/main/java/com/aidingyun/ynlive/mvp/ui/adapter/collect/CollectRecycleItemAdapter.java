package com.aidingyun.ynlive.mvp.ui.adapter.collect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CollectCourseModel;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;
import com.aidingyun.ynlive.mvp.ui.widget.swipe.SwipeItemLayout;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Administrator on 2015/11/24.
 */
public class CollectRecycleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CollectCourseModel.ListBean> results;
    UpdateVersionUtils updateVersionUtils = new UpdateVersionUtils();

    public CollectRecycleItemAdapter(Context context, List<CollectCourseModel.ListBean> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect_course, parent, false));
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

        LoadImage.loadNormalImage(context,results.get(position).getPhoto(),holder.item_recyc_type3_item_img);
        holder.tv_lable.setText(results.get(position).getTitle());
        holder.tv_grade.setText(" "+String.format("%.1f", Float.parseFloat(results.get(position).getScore())));
        holder.tv_collent.setText(results.get(position).getCollection());

        if (results.get(position).getType().equals("0")) { // 直播课
            if (results.get(position).getLive_type().equals("0")) {
                // 未直播
                holder.tv_live_status.setImageResource(R.mipmap.icon_coursetype_live);
            } else  {
                // 直播中
                holder.tv_live_status.setImageResource(R.mipmap.icon_course_living);
            }
        } else  { // 录播课
            holder.tv_live_status.setImageResource(R.mipmap.icon_coursetype_record);
        }

        if (results.get(position).getPrice().isEmpty()) {
            holder.tv_money.setVisibility(View.INVISIBLE);
        } else {
            holder.tv_money.setVisibility(View.VISIBLE);
            holder.tv_money.setText(results.get(position).getPrice());
        }

        holder.course_detail_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseDetailActivity.start(context,results.get(position).getCourseid());
            }
        });

        holder.tv_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> reqBody = new ConcurrentSkipListMap<>();
                reqBody.put("op", "lesson_remove");
                reqBody.put("sectionid", results.get(position).getCourseid());
                updateVersionUtils.postByName(Global.GET_COLLECT_SERVICE_NAME, reqBody, new HttpManager.Callback() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            if (code.equals("success")) {
                                results.remove(position);
                                notifyItemRemoved(position);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        ToastUtils.showError(context, error);
                    }
                });
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
        LinearLayout tv_delete_btn;
        LinearLayout course_detail_linear;

        public ItemViewHolder(View itemView) {
            super(itemView);
            item_recyc_type3_item_img = (ImageView) itemView.findViewById(R.id.item_recyc_type3_item_img);
            tv_lable = (TextView) itemView.findViewById(R.id.tv_lable);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
            tv_collent = (TextView) itemView.findViewById(R.id.tv_collent);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
            tv_unit = (TextView) itemView.findViewById(R.id.tv_unit);
            tv_live_status = (ImageView) itemView.findViewById(R.id.tv_live_status);
            tv_delete_btn = (LinearLayout) itemView.findViewById(R.id.tv_delete_btn);
            course_detail_linear = (LinearLayout) itemView.findViewById(R.id.course_detail_linear);
        }
    }
}
