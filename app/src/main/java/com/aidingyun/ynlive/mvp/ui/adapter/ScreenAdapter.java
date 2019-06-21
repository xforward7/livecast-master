package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.aidingyun.ynlive.mvp.model.entity.ScreenModel;

import java.util.List;

/**
 * 左侧菜单ListView的适配器
 *
 * @author Administrator
 */
public class ScreenAdapter extends BaseAdapter {

    private Context context;
    private int selectItem = 0;
    private List<ScreenModel> list;

    public ScreenAdapter(Context context, List<ScreenModel> list) {
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
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.material_red_400));
            convertView.setBackgroundColor(context.getResources().getColor(R.color.material_gray));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.material_white));
//            holder.tv_name.setBackgroundColor(context.getResources().getColor(R.color.material_gray));
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.material_black));
        }
        holder.tv_name.setPadding(10,10,0,0);
        holder.tv_name.setGravity(Gravity.LEFT|Gravity.CENTER);
        holder.tv_name.setText(list.get(position).getType_name());
        return convertView;
    }

    static class ViewHolder {
        private TextView tv_name;
    }
}
