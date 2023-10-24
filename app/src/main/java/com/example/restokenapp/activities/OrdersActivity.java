package com.example.restokenapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restokenapp.OrdersAdapter;
import com.example.restokenapp.R;
import com.example.restokenapp.models.Order;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    ArrayList<Order> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        mRecyclerView = findViewById(R.id.recyclerview);

        data.add(new Order(30, 1, "2023-08-27T21:17:17.308621Z"));
        data.add(new Order(56, 2, "2023-08-27T22:32:21.460874Z"));
        data.add(new Order(84, 3, "2023-08-27T22:34:18.159075Z"));
        data.add(new Order(33, 4, "2023-09-03T10:51:17.052316Z"));
        data.add(new Order(29, 5, "2023-09-03T11:12:33.53104Z"));

        OrdersAdapter adapter = new OrdersAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}