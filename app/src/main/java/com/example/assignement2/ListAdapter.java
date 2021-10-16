package com.example.assignement2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<List> lists;

    public ListAdapter(Context context, ArrayList<List> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list, null);
        }
        TextView nameText = (TextView) view.findViewById(R.id.n);
        TextView priceText = (TextView) view.findViewById(R.id.p);
        TextView qText = (TextView) view.findViewById(R.id.q);

        nameText.setText(lists.get(i).name);
        priceText.setText(lists.get(i).price);
        qText.setText(lists.get(i).q);

        return view;
    }

}
