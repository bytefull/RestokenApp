package com.example.restokenapp.activities;

import static android.content.ContentValues.TAG;
import static com.example.restokenapp.api.ApiClient.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.restokenapp.R;
import com.example.restokenapp.api.ApiClient;
import com.example.restokenapp.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    TextView usernameTextView;
    TextView balanceTextView;
    ApiClient apiClient;

    Call<User> apiCall;
    Retrofit retrofit;
    String fullToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameTextView = findViewById(R.id.username);
        balanceTextView = findViewById(R.id.balance);

        fullToken = getIntent().getStringExtra("fullToken");
        Log.i(TAG, "fullToken: " + fullToken);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClient = retrofit.create(ApiClient.class);

        apiCall = apiClient.getProfile(fullToken);

        apiCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = new User(
                            response.body().getEmail(),
                            response.body().getUsername(),
                            response.body().getAdmin(),
                            response.body().getUuid(),
                            response.body().getBalance()
                    );
                    Log.v(TAG, "onResponse: " + user);
                    usernameTextView.setText(user.getUsername().toString());
                    balanceTextView.setText(user.getBalance().toString());
                } else {
                    Log.w(TAG, "onResponse: response is not successful");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}