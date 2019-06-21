package com.aidingyun.ynlive.mvp.ui.adapter.collect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CollectCourseModel;
import com.aidingyun.ynlive.mvp.model.entity.CollectLiveroomModel;
import com.aidingyun.ynlive.mvp.ui.activity.course_detail.CourseDetailActivity;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Administrator on 2015/11/24.
 */
public class PKRecycleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CollectLiveroomModel.RoomInfo> results;
    UpdateVersionUtils updateVersionUtils = new UpdateVersionUtils();

    public PKRecycleItemAdapter(Context context, List<CollectLiveroomModel.RoomInfo> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect_room, parent, false));
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

        LoadImage.loadNormalImage(context,results.get(position).getImage(),holder.item_recyc_type3_item_img);
        holder.tv_lable.setText(results.get(position).getTitle());
        holder.livecontent_desc.setText(results.get(position).getIntro());
        holder.liveroom_livetime.setText(results.get(position).getCreate_time());

        holder.course_detail_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 前往直播间

            }
        });

        holder.tv_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> reqBody = new ConcurrentSkipListMap<>();
                reqBody.put("op", "room_remove");
                reqBody.put("roomid", results.get(position).getId());
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
        public ImageView tv_live_status;
        public TextView livecontent_desc;
        public TextView liveroom_livetime;
        public LinearLayout tv_delete_btn;
        public LinearLayout course_detail_linear;

        public ItemViewHolder(View itemView) {
            super(itemView);
            item_recyc_type3_item_img = (ImageView) itemView.findViewById(R.id.item_recyc_type3_item_img);
            tv_lable = (TextView) itemView.findViewById(R.id.tv_lable);
            tv_live_status = (ImageView) itemView.findViewById(R.id.tv_live_status);
            livecontent_desc = (TextView) itemView.findViewById(R.id.livecontent_desc);
            liveroom_livetime = (TextView) itemView.findViewById(R.id.liveroom_livetime);
            tv_delete_btn = (LinearLayout) itemView.findViewById(R.id.tv_delete_btn);
            course_detail_linear = (LinearLayout) itemView.findViewById(R.id.course_detail_linear);
        }
    }
}
