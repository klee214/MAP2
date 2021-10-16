package com.example.assignement2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    RecyclerView listView;
    HistoryAdapter adapter;
    ArrayList<History> historyArray;
    String TAG = "tagging";

    TextView dPrice;
    TextView dProduct;
    TextView dDate;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        listView = (RecyclerView) findViewById(R.id.historyView);
        if (getIntent().hasExtra("bundle")){
            Intent intent = getIntent();
            Bundle extra = intent.getBundleExtra("bundle");

            historyArray = extra.getParcelableArrayList("histories");

            Log.d(TAG, "onCreate: " + extra);
            Log.d(TAG, "onCreate: " + historyArray.get(0));
        }
        listView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(this, historyArray, new HistoryAdapter.OnItemClickListner() {
            @Override
            public void onHistoryClicked(History item) {
                setContentView(R.layout.details);

                dPrice = (TextView) findViewById(R.id.dPrice);
                dProduct = (TextView) findViewById(R.id.dProduct);
                dDate = (TextView) findViewById(R.id.dDate);

                dProduct.setText(item.nam);
                dPrice.setText(item.pric);
                dDate.setText(item.date);
            }
        });
        listView.setAdapter(adapter);

    }
}
