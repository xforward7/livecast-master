package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.FullyGridLayoutManager;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.ui.activity.search.SearchCourseActivity;
import com.aidingyun.ynlive.mvp.ui.widget.RecyclerViewBanner;
import com.aidingyun.ynlive.mvp.ui.widget.gallery.CardScaleHelper;
import com.aidingyun.ynlive.mvp.ui.widget.gallery.SpeedRecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeCourseModel.ChoiceCourseDataBean> choiceCourseDataBeanList = new ArrayList<>();
    HomeCourseModel homeCourseModel;

    //get & set
    public List<HomeCourseModel.ChoiceCourseDataBean> getResults() {
        return choiceCourseDataBeanList;
    }

    //type
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    public static final int TYPE_4 = 0xff04;
//    public static final int TYPE_MAIN = 0xffff;

    public RecycleAdapter(Context context,HomeCourseModel homeCourseModel) {
        this.context = context;
        this.homeCourseModel = homeCourseModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_1:
                return new MyViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type1, parent, false));
            case TYPE_2:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type2, parent, false));
            case TYPE_3:
                return new MyViewHolder3(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type3, parent, false));
            case TYPE_4:
                return new MyViewHolder4(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type4, parent, false));
//            case TYPE_MAIN:
//                return new MyViewHolderMain(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_typemain, parent, false));
            default:
                Log.d("error","viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1){
            bindType1((MyViewHolder1) holder, position);
        }else if (holder instanceof MyViewHolder2){
            bindType2((MyViewHolder2) holder, position);
        }else if (holder instanceof MyViewHolder3){
            bindType3((MyViewHolder3) holder, position);
        }else if (holder instanceof MyViewHolder4){
            bindType4((MyViewHolder4) holder, position);
        }
//        else if (holder instanceof MyViewHolderMain){
//            bindTypeMain((MyViewHolderMain) holder, position);
//        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_1;
        }else if (position == 1){
            return TYPE_2;
        }else if (position == 2){
            return TYPE_3;
        }else if (position == 3){
            return TYPE_4;
//        }else {
//            return TYPE_MAIN;
        }
        return position;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type){
                        case TYPE_1:
                        case TYPE_2:
                        case TYPE_3:
                        case TYPE_4:
                            return gridManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    /////////////////////////////

    private void bindType1(MyViewHolder1 holder, int position){
        holder.recyclerViewBanner.setRvBannerData(homeCourseModel.getBanner_data());
        holder.recyclerViewBanner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int position, AppCompatImageView bannerView) {
//                Glide.with(bannerView.getContext())
//                        .load(homeCourseModel.getBanner_data().get(position).getAdvertisement_picture())
//                        .into(bannerView);
                LoadImage.loadNormalImage(context,homeCourseModel.getBanner_data().get(position).getAdvertisement_picture(),bannerView);
            }
        });

        holder.recyclerViewBanner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
            @Override
            public void onClick(int position) {

            }
        });
    }
    private CardScaleHelper mCardScaleHelper = null;
    List<HomeCourseModel.ChoiceCourseDataBean> choiceCourseDataBeans = new ArrayList<>();
    private void bindType2(MyViewHolder2 holder, int position){

        if (choiceCourseDataBeanList.size()!=0){
            choiceCourseDataBeanList.clear();
        }



        if (homeCourseModel!=null) {
            int size=homeCourseModel.getChoice_course_data().size();
            size=size>3?3:0;
            for (int i = 0; i < size; i++) {
                choiceCourseDataBeanList.add(homeCourseModel.getChoice_course_data().get(i));
//            choiceCourseDataBeanList.remove(0);
            }
        }
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setAdapter(new CardAdapter(context,choiceCourseDataBeanList,3));
        holder.recyclerView.smoothScrollToPosition(1);

        // mRecyclerView绑定scale效果
//        mCardScaleHelper = new CardScaleHelper();
//        mCardScaleHelper.setCurrentItemPos(1);
//        mCardScaleHelper.attachToRecyclerView(holder.recyclerView);

        PagerSnapHelper mPagerSnapHelper = new PagerSnapHelper();
        mPagerSnapHelper.attachToRecyclerView(holder.recyclerView);
        if (choiceCourseDataBeans.size()!=0){
            choiceCourseDataBeans.clear();
        }
        if (homeCourseModel!=null) {
            for (int i = 3; i < homeCourseModel.getChoice_course_data().size(); i++) {
                choiceCourseDataBeans.add(homeCourseModel.getChoice_course_data().get(i));
            }
        }
        holder.item_recyc_type2.setLayoutManager(new FullyGridLayoutManager(holder.item_recyc_type2.getContext(), 2, GridLayoutManager.VERTICAL, false));
        holder.item_recyc_type2.setAdapter(new RecycleItemAdapterChoiceCourse(context, choiceCourseDataBeans));
        holder.item_recyc_type2.setNestedScrollingEnabled(false);

        holder.tv_check_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 跳转到更多
                 */
//                SearchCourseActivity.start(context,homeCourseModel.getChoice_course_data());
            }
        });
    }


    private void bindType3(MyViewHolder3 holder, int position){
        holder.item_recyc_type3.setLayoutManager(new FullyGridLayoutManager(holder.item_recyc_type3.getContext(), 1, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        holder.title_linear.setVisibility(View.GONE);
        holder.tv_check_more.setVisibility(View.GONE);
        holder.item_recyc_type3.setAdapter(new RecycleAdapterTypeCourse(context, homeCourseModel.getType_data()));
        holder.item_recyc_type3.setNestedScrollingEnabled(false);
    }

    private void bindType4(MyViewHolder4 holder, int position){
//        holder.item_recyc_type4.setLayoutManager(new LinearLayoutManager(holder.item_recyc_type4.getContext(), LinearLayoutManager.VERTICAL, false));
        holder.item_recyc_type4.setLayoutManager(new FullyGridLayoutManager(holder.item_recyc_type4.getContext(), 1, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        LoadImage.loadNormalImage(context,homeCourseModel.getHost_data().getBanner(),holder.top_image);
        holder.item_recyc_type4.setAdapter(new RecycleItemAdapterType4(context, homeCourseModel.getHost_data().getHost_list()));
        holder.item_recyc_type4.setNestedScrollingEnabled(false);
        holder.tv_check_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 跳转到更多
                 */
//                SearchCourseActivity.start(context,homeCourseModel.getHost_data().getHost_list());
            }
        });

    }

//    private void bindTypeMain(MyViewHolderMain holder, int position){
//        String img = "//pica.nipic.com/2007-10-09/200710994020530_2.jpg";
//        LoadImage.loadNormalImage(context,img,holder.item_imgmain);
//    }

    /////////////////////////////

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        RecyclerViewBanner recyclerViewBanner;

        public MyViewHolder1(View itemView) {
            super(itemView);
            recyclerViewBanner = (RecyclerViewBanner) itemView.findViewById(R.id.recyclerViewBanner);
        }
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type2;
        public RecyclerView recyclerView;
        public TextView tv_check_more;

        public MyViewHolder2(final View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            item_recyc_type2 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type2);
            tv_check_more = (TextView) itemView.findViewById(R.id.tv_check_more);
        }
    }
    public class MyViewHolder3 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type3;
        public ImageView top_image;
        public TextView tv_type_name;
        public LinearLayout title_linear;
        public TextView tv_check_more;
        public MyViewHolder3(final View itemView) {
            super(itemView);
            item_recyc_type3 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type3);
            top_image = (ImageView) itemView.findViewById(R.id.top_image);
            tv_type_name = (TextView) itemView.findViewById(R.id.tv_type_name);
            title_linear = (LinearLayout) itemView.findViewById(R.id.title_linear);
            tv_check_more = (TextView) itemView.findViewById(R.id.tv_check_more);
        }
    }

    public class MyViewHolder4 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type4;
        public ImageView top_image;
        public TextView tv_type_name;
        public TextView tv_check_more;

        public MyViewHolder4(final View itemView) {
            super(itemView);
            item_recyc_type4 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type4);
            top_image = (ImageView) itemView.findViewById(R.id.top_image);
            tv_type_name = (TextView) itemView.findViewById(R.id.tv_type_name);
            tv_check_more = (TextView) itemView.findViewById(R.id.tv_check_more);
        }
    }

//    public class MyViewHolderMain extends RecyclerView.ViewHolder {
//        public ImageView item_imgmain;
//        public RecyclerView item_recyc_expert;
//
//        public MyViewHolderMain(final View itemView) {
//            super(itemView);
//            item_imgmain = (ImageView) itemView.findViewById(R.id.top_image);
//            item_recyc_expert = (RecyclerView) itemView.findViewById(R.id.item_recyc_expert);
//        }
//    }

    private class Banner {

        String url;

        public Banner(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
