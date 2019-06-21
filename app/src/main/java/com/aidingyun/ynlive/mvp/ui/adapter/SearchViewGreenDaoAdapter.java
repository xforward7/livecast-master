package com.aidingyun.ynlive.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.greendao.model.User;

import java.util.List;


/**
 * Created by Wu on 2017/4/28 0028 上午 9:40.
 * 描述：
 */
public class SearchViewGreenDaoAdapter extends BaseAdapter {
    private Context context;
    private List<User> list;

    public SearchViewGreenDaoAdapter(Context context, List<User> list) {
        this.context = context;
        this.list=list;
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
        final User tv = (User) getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_category, null);
            viewHolder = new ViewHolder();
            viewHolder.flowlayout_tv = (TextView) convertView.findViewById(R.id.item_home_name);
//            viewHolder.flowlayout_tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context,tv.getName()+"",Toast.LENGTH_SHORT).show();
//                }
//            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.flowlayout_tv.setText(tv.getName());

        return convertView;
    }

    //创建ViewHolder类
    class ViewHolder {
        TextView flowlayout_tv;

    }
}
