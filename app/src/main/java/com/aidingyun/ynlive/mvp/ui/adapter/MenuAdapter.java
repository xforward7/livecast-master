package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;

import java.util.List;

/**
 * 左侧菜单ListView的适配器
 *
 * @author Administrator
 */
public class MenuAdapter extends BaseAdapter {

    private Context context;
    private int selectItem = 0;
    private List<CourseClassificationModel.DataBean> list;

    public MenuAdapter(Context context, List<CourseClassificationModel.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_menu, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == selectItem) {
//            holder.tv_name.setBackgroundColor(Color.WHITE);
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.material_black));
//            arg1.setBackgroundResource(R.drawable.tongcheng_all_bg01);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
//            holder.tv_name.setBackgroundColor(context.getResources().getColor(R.color.material_gray));
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.material_gray));
        }
        holder.tv_name.setText(list.get(position).getType_name());
        return convertView;
    }

    static class ViewHolder {
        private TextView tv_name;
    }
}
