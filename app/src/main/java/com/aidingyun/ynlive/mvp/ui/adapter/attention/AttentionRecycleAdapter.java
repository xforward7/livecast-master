package com.aidingyun.ynlive.mvp.ui.adapter.attention;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.model.entity.MineAttentionModel;
import com.aidingyun.ynlive.mvp.ui.activity.attention.ExpertDetailsActivity;
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;
import com.aidingyun.ynlive.mvp.ui.widget.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class AttentionRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private MineAttentionModel result;

    UpdateVersionUtils updateVersionUtils = new UpdateVersionUtils();
    public AttentionRecycleAdapter(Context context, MineAttentionModel result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_attention_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            bind((ItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return result.getData().getList().size();
    }

    private void bind(ItemViewHolder holder, int position){

        List<MineAttentionModel.AttentionBean> attentionBeans = result.getData().getList();
        MineAttentionModel.AttentionBean attentionBean = attentionBeans.get(position);

        LoadImage.loadCircleImage(context,attentionBean.getIcon(),holder.circle_head);
        holder.tv_lable.setText(attentionBean.getHost_name());
        holder.tv_grade.setText(" "+String.format("%.1f", Float.parseFloat(attentionBean.getScore())));
        holder.tv_technical.setText(TextUtils.isEmpty(attentionBean.getIntro()) ? "暂无简介" : attentionBean.getIntro());
        holder.tv_course.setText(attentionBean.getCourse_amounts()+"门课程");
        holder.tv_collent.setText(attentionBean.getAttention_amounts());
        holder.tv_recommend_learning.setText("暂无推荐");

        holder.mine_attention_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 跳往专家详情页
                ExpertDetailsActivity.start(context, attentionBean.getFocused_userid());
            }
        });

        holder.tv_cancel_attention_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Map<String, String> reqBody = new ConcurrentSkipListMap<>();
//                reqBody.put("focused_userid", attentionBean.getFocused_userid());
//                updateVersionUtils.postByName(Global.GET_COLLECT_SERVICE_NAME, reqBody, new HttpManager.Callback() {
//                    @Override
//                    public void onResponse(String result) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(result);
//                            String code = jsonObject.getString("code");
//                            if (code.equals("success")) {
//                                attentionBeans.remove(position);
//                                notifyItemRemoved(position);
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(String error) {
//                        ToastUtils.showError(context, error);
//                    }
//                });
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView circle_head;
        public TextView tv_lable;
        public TextView tv_grade;
        public TextView tv_technical;
        public TextView tv_course;
        public TextView tv_collent;
        public TextView tv_recommend_learning;
        LinearLayout tv_cancel_attention_btn;
        AutoLinearLayout mine_attention_linear;

        public ItemViewHolder(View itemView) {
            super(itemView);
            circle_head = (CircleImageView) itemView.findViewById(R.id.circle_head);
            tv_lable = (TextView) itemView.findViewById(R.id.tv_lable);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
            tv_technical = (TextView) itemView.findViewById(R.id.tv_technical);
            tv_course = (TextView) itemView.findViewById(R.id.tv_course);
            tv_collent = (TextView) itemView.findViewById(R.id.tv_collent);
            tv_recommend_learning = (TextView) itemView.findViewById(R.id.tv_recommend_learning);
            tv_cancel_attention_btn = (LinearLayout) itemView.findViewById(R.id.tv_cancel_attention_btn);
            mine_attention_linear = (AutoLinearLayout) itemView.findViewById(R.id.mine_attention_linear);
        }
    }
}
