package com.accenture.android.test.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accenture.android.framework.util.InfiniteListAdapter;
import com.accenture.android.test.R;
import com.accenture.android.test.activity.InfiniteListActivity;

import java.util.ArrayList;

public class MyInfiniteListAdapter<T> extends InfiniteListAdapter<T> {

    private Activity activity;
    private int layoutRes;
    private ArrayList<T> itemList;

    public MyInfiniteListAdapter(Activity activity, int layoutRes, ArrayList<T> itemList) {

        super(activity, layoutRes, itemList);

        this.activity = activity;
        this.layoutRes = layoutRes;
        this.itemList = itemList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(layoutRes, parent, false);

            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String text = (String) itemList.get(position);
        if (text != null) {
            holder.text.setText(text);
        }

        return convertView;
    }

    @Override
    public void onNewLoadRequired() {
        ((InfiniteListActivity)activity).loadNewItems();
    }

    static class ViewHolder {
        TextView text;
    }

}