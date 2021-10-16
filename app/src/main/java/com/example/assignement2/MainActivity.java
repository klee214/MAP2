package com.example.assignement2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListAdapter adapter;

    TextView type;
    TextView qty;
    TextView price;

    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button c;

    AlertDialog.Builder builder;

    ArrayList<List> listArray;
    ArrayList<History> historyArray;

    String tmpText = "";
    int int_qty = 0;
    double double_each_price = 0;
    int total_qty = 0;
    String string_type = "";
    double ttl_price = 0;
    String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        c = (Button) findViewById(R.id.c);

        type = (TextView) findViewById(R.id.type);
        qty = (TextView) findViewById(R.id.qty);
        price = (TextView) findViewById(R.id.price);
        builder = new AlertDialog.Builder(this);

        if (savedInstanceState == null){
            List pants = new List("Pants", "20.44","10");
            List shoes = new List("Shoes", "10.44","100");
            List hats = new List("Hats", "5.9","30");

            listArray = new ArrayList<>();
            listArray.add(pants);
            listArray.add(shoes);
            listArray.add(hats);

            historyArray = new ArrayList<>();
        }

        else{
            listArray = savedInstanceState.getParcelableArrayList("allLists");
            historyArray = savedInstanceState.getParcelableArrayList("hisLists");
        }


        listView = (ListView) findViewById(R.id.listview);
        adapter = new ListAdapter(this, listArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tmpText = "";
                int_qty = 0;

                double_each_price = Double.parseDouble(listArray.get(i).price);
                total_qty = Integer.parseInt(listArray.get(i).q);
                string_type = listArray.get(i).name;
                type.setText(string_type);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("hisLists", listArray);
        outState.putParcelableArrayList("allLists", listArray);
    }

    public void numClick(View v) {
        if(string_type != ""){
            String text = ((Button) v).getText().toString();
            tmpText += text;

            int_qty = Integer.parseInt(tmpText);
            ttl_price = double_each_price * int_qty;

            price.setText(String.valueOf(ttl_price));
            qty.setText(tmpText);
        }
    }

    public void buyButton(View v) {
        if(string_type == ""){
            Toast.makeText(this,"SELECT ITEM PLEASE!!!",Toast.LENGTH_LONG).show();
        }else if(total_qty < int_qty){
            Toast.makeText(this,"NOT ENOUGH QUANTITY!!!",Toast.LENGTH_LONG).show();
        }else{

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formatDate = df.format(c);

            History newHis = new History(string_type, String.valueOf(ttl_price),String.valueOf(int_qty), formatDate);
            historyArray.add(newHis);

            builder.create();
            builder.setTitle("Thank you for donation");
            builder.setMessage("Your purchase is " + int_qty + " " + string_type + " for " + ttl_price);
            builder.show();
        }
    }
    public void managerButton(View v) {
        setContentView(R.layout.manager_activity);
    }
    public void hisClick(View v) {
        Intent hisIntent =new Intent(this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("histories", historyArray);
        hisIntent.putExtra("bundle",bundle);
        startActivity(hisIntent);
    }
}