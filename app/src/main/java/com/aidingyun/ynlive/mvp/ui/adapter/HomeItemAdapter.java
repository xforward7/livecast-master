package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author：wangzihang
 * date： 2017/8/8 19:15
 * desctiption：
 * e-mail：wangzihang@xiaohongchun.com
 */

public class HomeItemAdapter extends BaseAdapter {

    private Context context;
    private List<CourseClassificationModel.DataBean.TypeDataBeanX.TypeDataBean> foodDatas;
    private int selectItem = -1;
    public HomeItemAdapter(Context context, List<CourseClassificationModel.DataBean.TypeDataBeanX.TypeDataBean> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
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
        CourseClassificationModel.DataBean.TypeDataBeanX.TypeDataBean subcategory = foodDatas.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_category, null);
            viewHold = new ViewHold();
            viewHold.tv_name = (TextView) convertView.findViewById(R.id.item_home_name);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        if (position == selectItem) {
            viewHold.tv_name.setBackgroundResource(R.drawable.btn_select_text_blue_shape);
            viewHold.tv_name.setTextColor(context.getResources().getColor(R.color.material_white));
        } else {
            viewHold.tv_name.setBackgroundResource(R.drawable.btn_select_text_white_shape);
            viewHold.tv_name.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        viewHold.tv_name.setText(subcategory.getType_name());
        return convertView;


    }

    private static class ViewHold {
        private TextView tv_name;
        private SimpleDraweeView iv_icon;
    }

}
