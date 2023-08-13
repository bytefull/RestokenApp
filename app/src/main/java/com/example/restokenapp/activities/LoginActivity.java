package com.example.restokenapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.restokenapp.R;
import com.example.restokenapp.api.ApiClient;
import com.example.restokenapp.models.Token;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    EditText emailEditText;
    EditText passwordEditText;
    String fullToken;
    Intent loginIntent;
    ApiClient apiClient;

    Call<Token> apiCall;
    Retrofit retrofit;
    private static final String BASE_URL = "http://172.30.15.113:8000/";
    private static final String TAG = "LoginActivityTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClient = retrofit.create(ApiClient.class);

        loginButton.setOnClickListener(view -> {
            if ((emailEditText.getText() != null) && (passwordEditText.getText() != null)) {
                apiCall = apiClient.login(emailEditText.getText().toString(),
                        passwordEditText.getText().toString());
                apiCall.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if (response.isSuccessful()) {
                            fullToken = response.body().getFullToken();
                            Log.i(TAG, "onResponse: " + fullToken);
                            loginIntent = new Intent(LoginActivity.this, ProfileActivity.class);
                            loginIntent.putExtra("fullToken", fullToken);
                            startActivity(loginIntent);
                        } else {
                            Log.v(TAG, "response is not successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Snackbar.make(findViewById(R.id.linearLayout),
                                        "Failed to login", Snackbar.LENGTH_SHORT)
                                .show();
                        Log.v(TAG, t.getMessage());
                    }
                });
            } else {
                Snackbar.make(findViewById(R.id.linearLayout),
                        "Missing field(s)",
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}