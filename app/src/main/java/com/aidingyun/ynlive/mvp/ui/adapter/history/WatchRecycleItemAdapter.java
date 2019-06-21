package com.aidingyun.ynlive.mvp.ui.adapter.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.model.entity.HistoryBean;
import com.aidingyun.ynlive.mvp.model.entity.SearchCourseModel;
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;
import com.google.gson.Gson;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Administrator on 2015/11/24.
 */
public class WatchRecycleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HistoryBean.ListBean> results;
    UpdateVersionUtils updateVersionUtils = new UpdateVersionUtils();

    public WatchRecycleItemAdapter(Context context, List<HistoryBean.ListBean> results) {
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

    /////////////////////////////

    private void bind(ItemViewHolder holder, int position){
        holder.tv_lable.setText(results.get(position).getSection_title());

        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("courseid", results.get(position).getCourseid());
        updateVersionUtils.postByName(Global.GET_COURSE_DETAIL_SERVICE_NAME, reqBody, new HttpManager.Callback() {
                    @Override
                    public void onResponse(String result) {
                        Log.e("WatchRecycleItemAdapter", "result++++++++++++++++" + result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            if (code.equals("success")) {
                                Gson gson = new Gson();
                                ABaseService.courseDetailInfo = gson.fromJson(result, CourseDetailInfo.class);
                                LoadImage.loadNormalImage(context,ABaseService.courseDetailInfo.getData().getPhoto(),holder.item_recyc_type3_item_img);
                                holder.tv_grade.setText(ABaseService.courseDetailInfo.getData().getScore()+"分");
                                holder.tv_collent.setText(ABaseService.courseDetailInfo.getData().getPeopel_count());
                                holder.tv_money.setText(ABaseService.courseDetailInfo.getData().getPrice());
                                if (ABaseService.courseDetailInfo.getData().getLive_status().equals("0")) {
                                    holder.tv_live_status.setText(ABaseService.courseDetailInfo.getData().getType().equals("0") ? "直播课":"录播课");
                                    holder.tv_live_status.setBackgroundResource(R.drawable.item_bg_blue_r4);
                                }else {
                                    holder.tv_live_status.setBackgroundResource(R.drawable.red_r4);
                                    holder.tv_live_status.setText("直播中");
                                }

                            }
                        }catch (Exception e){

                        }
                    }

            @Override
            public void onError(String error) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDetailActivity.start(context,results.get(position).getCourseid());
            }
        });

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_recyc_type3_item_img;
        public TextView tv_lable;
        public TextView tv_grade;
        public TextView tv_collent;
        public TextView tv_money;
        TextView tv_live_status;

        public ItemViewHolder(View itemView) {
            super(itemView);
            item_recyc_type3_item_img = (ImageView) itemView.findViewById(R.id.item_recyc_type3_item_img);
            tv_lable = (TextView) itemView.findViewById(R.id.tv_lable);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
            tv_collent = (TextView) itemView.findViewById(R.id.tv_collent);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
            tv_live_status = (TextView) itemView.findViewById(R.id.tv_live_status);
        }
    }
}
