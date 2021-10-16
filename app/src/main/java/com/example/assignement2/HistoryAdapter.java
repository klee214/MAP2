package com.example.assignement2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder> {
    Context context;
    ArrayList<History> hist;

    public interface OnItemClickListner{
        void onHistoryClicked(History item);
    }

    private final OnItemClickListner listner;

    public HistoryAdapter(Context context, ArrayList<History> lists, OnItemClickListner listnerFromActivity) {
        this.context = context;
        this.hist = lists;
        listner = listnerFromActivity;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView nameText;
        private final TextView priceText;
        private final TextView qText;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.na);
            priceText = itemView.findViewById(R.id.pr);
            qText = itemView.findViewById(R.id.qu);
        }

        public TextView getNameText() {
            return nameText;
        }

        public TextView getPriceText() {
            return priceText;
        }

        public TextView getQText() {
            return qText;
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater =  LayoutInflater.from(context);
        View view =  myInflater.inflate(R.layout.history_list,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.viewHolder holder, int position) {
        holder.getNameText().setText(hist.get(position).nam);
        holder.getPriceText().setText(hist.get(position).pric);
        holder.getQText().setText(hist.get(position).q);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onHistoryClicked(hist.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return hist.size();
    }

}
