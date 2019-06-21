package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.aidingyun.ynlive.mvp.ui.activity.search.SearchCourseActivity;
import com.aidingyun.ynlive.mvp.ui.widget.GridViewForScrollView;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * 右侧主界面ListView的适配器
 *
 * @author Administrator
 */
public class HomeAdapter extends BaseAdapter {

    private Context context;
    private List<CourseClassificationModel.DataBean.TypeDataBeanX> foodDatas;

    public HomeAdapter(Context context, List<CourseClassificationModel.DataBean.TypeDataBeanX> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }

    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseClassificationModel.DataBean.TypeDataBeanX dataBean = foodDatas.get(position);
        List<CourseClassificationModel.DataBean.TypeDataBeanX.TypeDataBean> dataList = dataBean.getType_data();
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home, null);
            viewHold = new ViewHold();
            viewHold.gridView = (GridViewForScrollView) convertView.findViewById(R.id.gridView);
//            viewHold.iv_banner = (ImageView) convertView.findViewById(R.id.iv_banner);
            viewHold.tv_level = (TextView) convertView.findViewById(R.id.tv_level);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        HomeItemAdapter adapter = new HomeItemAdapter(context, dataList);
        viewHold.tv_level.setText(dataBean.getType_name());
        viewHold.gridView.setAdapter(adapter);
        viewHold.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                adapter.setSelectItem(position);
//                adapter.notifyDataSetInvalidated();
                SearchCourseActivity.start(context,dataList.get(position).getType_name());
            }
        });
        return convertView;
    }

    private static class ViewHold {
        private GridViewForScrollView gridView;
        private TextView tv_level;
        private ImageView iv_banner;
    }

}
