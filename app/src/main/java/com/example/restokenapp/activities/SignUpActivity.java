package com.example.restokenapp.activities;

import static android.content.ContentValues.TAG;
import static com.example.restokenapp.api.ApiClient.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.restokenapp.R;
import com.example.restokenapp.api.ApiClient;
import com.example.restokenapp.models.CreateUser;
import com.example.restokenapp.models.GetUser;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    Button signUpButton;
    EditText emailEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText passwordRetypeEditText;
    Intent loginIntent;
    ApiClient apiClient;
    Call<GetUser> apiCall;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpButton = findViewById(R.id.signUpButton);
        emailEditText = findViewById(R.id.email);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        passwordRetypeEditText = findViewById(R.id.passwordRetype);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClient = retrofit.create(ApiClient.class);

        signUpButton.setOnClickListener(view -> {
            if (validateUserForm(emailEditText, usernameEditText, passwordEditText, passwordRetypeEditText)) {
                apiCall = apiClient.signUp(new CreateUser(emailEditText.getText().toString(),
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()));

                apiCall.enqueue(new Callback<GetUser>() {
                    @Override
                    public void onResponse(@NonNull Call<GetUser> call, @NonNull Response<GetUser> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Log.i(TAG, "onResponse: " + response.body());
                            Snackbar.make(findViewById(R.id.linearLayout),
                                            "User created, login to continue", Snackbar.LENGTH_LONG)
                                    .show();
                            loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                            loginIntent.putExtra("email", response.body().getEmail());
                            startActivity(loginIntent);
                        } else {
                            Log.v(TAG, "response is not successful");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GetUser> call, @NonNull Throwable t) {
                        Snackbar.make(findViewById(R.id.linearLayout),
                                        "Failed to sign up", Snackbar.LENGTH_SHORT)
                                .show();
                        Log.v(TAG, Objects.requireNonNull(t.getMessage()));
                    }
                });
            } else {
                Snackbar.make(findViewById(R.id.linearLayout), "Failed to signup", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateUserForm(
            EditText emailEditText,
            EditText usernameEditText,
            EditText passwordEditText,
            EditText passwordRetypeEditText) {
        boolean result;

        result = (emailEditText.getText() != null) &&
                (usernameEditText.getText() != null) &&
                (passwordEditText.getText() != null) &&
                (passwordRetypeEditText.getText() != null) &&
                (passwordRetypeEditText.getText().toString().equals(passwordEditText.getText().toString()));
        return result;
    }
}