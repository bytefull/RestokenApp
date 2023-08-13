package com.example.restokenapp.activities;

import static android.content.ContentValues.TAG;
import static com.example.restokenapp.api.ApiClient.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.restokenapp.R;
import com.example.restokenapp.api.ApiClient;
import com.example.restokenapp.models.GetUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    TextView usernameTextView;
    TextView balanceTextView;
    ApiClient apiClient;

    Call<GetUser> apiCall;
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

        apiCall.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(@NonNull Call<GetUser> call, @NonNull Response<GetUser> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    GetUser getUser = new GetUser(
                            response.body().getEmail(),
                            response.body().getUsername(),
                            response.body().getAdmin(),
                            response.body().getUuid(),
                            response.body().getBalance()
                    );
                    Log.v(TAG, "onResponse: " + getUser);
                    usernameTextView.setText(getUser.getUsername());
                    balanceTextView.setText(String.format(getUser.getBalance().toString()));
                } else {
                    Log.w(TAG, "onResponse: response is not successful");
                }
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}