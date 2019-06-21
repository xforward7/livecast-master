package com.aidingyun.ynlive.mvp.ui.activity.course_detail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.model.entity.CourseOrderInfo;
import com.aidingyun.ynlive.mvp.ui.activity.account.LoginActivity;
import com.aidingyun.ynlive.mvp.ui.activity.account.RechargeActivity;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemBuyCourseAdapter;
import com.google.gson.Gson;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConfirmOrderActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    CheckBox buy_checkbox_course;
    ImageView iv_back;
    TextView tv_title;
    TextView tv_total_price;
    TextView tv_submit_btn;
    RecycleItemBuyCourseAdapter buyCourseAdapter;
    //这个是checkbox的Hashmap集合
    HashMap<String, Boolean> isSelected = new HashMap<>();
    List<CourseDetailInfo.SectionBean> sectionBeans = new ArrayList<>();
    private UpdateVersionUtils updateVersionUtils = null;
    String sectionid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        updateVersionUtils = new UpdateVersionUtils();
        recycler_view = findViewById(R.id.recycler_view);
        buy_checkbox_course = findViewById(R.id.buy_checkbox_course);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_total_price = findViewById(R.id.tv_total_price);
        tv_submit_btn = findViewById(R.id.tv_submit_btn);
        recycler_view.setLayoutManager(new GridLayoutManager(recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));
        if (ABaseService.courseDetailInfo!=null){
            sectionBeans = ABaseService.courseDetailInfo.getSection();
        }


        buyCourseAdapter = new RecycleItemBuyCourseAdapter(this,sectionBeans);
        recycler_view.setAdapter(buyCourseAdapter);
        buy_checkbox_course.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        checkAll();
                        tv_total_price.setText(ABaseService.courseDetailInfo.getData().getPrice());
                    }else {
//                        removeAll();
                        isSelected.clear();
                        tv_total_price.setText("0.00");
                    }
                    buyCourseAdapter.notifyDataSetChanged();
            }
        });


        tv_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                if (isSelected.size() == 0) {
                    ToastUtils.show(ConfirmOrderActivity.this,"您还没有选择要购买的课时!");
                    return;
                }
                for (int i = 0; i < sectionBeans.size(); i++) {
                    CourseDetailInfo.SectionBean sectionBean = sectionBeans.get(i);
                    Boolean isChecked = isSelected.get(sectionBean.getSectionid());
                    if (isChecked != null && isChecked) {
                        count++;
                        if (count == 1) {
                            sectionid = sectionBean.getSectionid();
                        }
                        if (count>1) {
                            sectionid += ","+sectionBean.getSectionid();
                        }
                    }
                }
                if (ABaseService.islogin) {
                    searchCourseBuy(ABaseService.loginInfo.getUserid(),
                            ABaseService.loginInfo.getUsername(),
                            ABaseService.courseDetailInfo.getData().getCourseid(),
                            ABaseService.courseDetailInfo.getData().getTitle(),
                            ABaseService.courseDetailInfo.getData().getPrice(),
                            sectionid,
                            ABaseService.courseDetailInfo.getData().getType());
                }else {
                    LoginActivity.start(ConfirmOrderActivity.this);
                }
            }
        });
    }


    private void searchCourseBuy(String userid,String username,String goods_id,String goods_name,String total_fee,String sectionid,String type) {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("userid", userid);
        reqBody.put("username", username);
        reqBody.put("goods_id", goods_id);
        reqBody.put("goods_name", goods_name);
        reqBody.put("total_fee", total_fee);
        reqBody.put("sectionid", sectionid);
        reqBody.put("type", type);
        updateVersionUtils.postByName(Global.GET_COURSE_BUY_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("ConfirmOrderActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("SUCCESS")){
                        Gson gson = new Gson();
//                        CourseOrderInfo courseOrderInfo = gson.fromJson(result,CourseOrderInfo.class);
                        ToastUtils.showError(ConfirmOrderActivity.this,jsonObject.getString("msg"));
                        finish();
                    }else{
                        ToastUtils.showError(ConfirmOrderActivity.this,jsonObject.getString("msg"));
                        RechargeActivity.start(ConfirmOrderActivity.this);
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("ConfirmOrderActivity","updateVersionUtils++++++++++++++++"+error);
            }
        });

    }

    /**
     * 全选，将数据加入inCartMap
     */
    private void checkAll() {
        for (CourseDetailInfo.SectionBean sectionBean:sectionBeans) {
            isSelected.put(sectionBean.getSectionid(),true);
        }
    }

    /**
     * 全选，将数据加入inCartMap
     */
    private void removeAll() {
        for (CourseDetailInfo.SectionBean sectionBean:sectionBeans) {
            isSelected.remove(sectionBean.getSectionid());
        }
    }

        class RecycleItemBuyCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context context;
        private List<CourseDetailInfo.SectionBean> sectionBeans;
        private int selectItem = 0;
        boolean all = false;
        //这个是checkbox的Hashmap集合
        public boolean getAll() {
            return all;
        }

        public void setAll(boolean all) {
            this.all = all;
        }


        public RecycleItemBuyCourseAdapter(Context context, List<CourseDetailInfo.SectionBean> sectionBeans) {
            this.context = context;
            this.sectionBeans = sectionBeans;
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
            holder.tv_no.setText("0"+(position+1));
            // 根据isSelected来设置checkbox的选中状况
            if (sectionBeans.size()!=0){
            CourseDetailInfo.SectionBean sectionBean = sectionBeans.get(position);
                if (sectionBean!=null){
                    if (isSelected.size()!=0){
                        Boolean isCheck = isSelected.get(sectionBean.getSectionid());
                        if (isCheck){
                            holder.checkbox_buy_item.setChecked(true);
                        }else {
                            holder.checkbox_buy_item.setChecked(false);
                        }
                    }else {
                        holder.checkbox_buy_item.setChecked(false);
                    }
                }
            }

            holder.checkbox_buy_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        isSelected.put(sectionBeans.get(position).getCourseid(),isChecked);
                        if (isSelected.size()==sectionBeans.size()){
                            buy_checkbox_course.setChecked(true);
                        }
                    }else {
                        buy_checkbox_course.setChecked(isChecked);
                        isSelected.remove(position);
                    }
                }
            });
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_no;
            public TextView tv_course_title;
            public TextView tv_time;
            public CheckBox checkbox_buy_item;


            public ItemViewHolder(View itemView) {
                super(itemView);
                tv_no = (TextView) itemView.findViewById(R.id.tv_no);
                tv_course_title = (TextView) itemView.findViewById(R.id.tv_course_title);
                tv_time = (TextView) itemView.findViewById(R.id.tv_time);
                checkbox_buy_item = (CheckBox) itemView.findViewById(R.id.checkbox_buy_item);
            }
        }
    }

}
