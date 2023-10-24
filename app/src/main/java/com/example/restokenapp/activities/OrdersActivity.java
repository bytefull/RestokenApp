package com.example.restokenapp.activities;

import static android.content.ContentValues.TAG;
import static com.example.restokenapp.api.ApiClient.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.restokenapp.OrdersAdapter;
import com.example.restokenapp.R;
import com.example.restokenapp.api.ApiClient;
import com.example.restokenapp.models.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ApiClient apiClient;
    Call<List<Order>> apiCall;
    Retrofit retrofit;
    String fullToken;
    String userId;

    ArrayList<Order> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        mRecyclerView = findViewById(R.id.recyclerview);

        fullToken = getIntent().getStringExtra("fullToken");
        userId = getIntent().getStringExtra("userId");

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClient = retrofit.create(ApiClient.class);

        apiCall = apiClient.getUserOrders(fullToken, userId);

        apiCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<Order> orders = response.body();
                    data.addAll(orders);
                    OrdersAdapter adapter = new OrdersAdapter(data);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                } else {
                    Log.w(TAG, "onResponse: response is not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        /*data.add(new Order(30, 1, "2023-08-27T21:17:17.308621Z"));
        data.add(new Order(56, 2, "2023-08-27T22:32:21.460874Z"));
        data.add(new Order(84, 3, "2023-08-27T22:34:18.159075Z"));
        data.add(new Order(33, 4, "2023-09-03T10:51:17.052316Z"));
        data.add(new Order(29, 5, "2023-09-03T11:12:33.53104Z"));*/
    }
}