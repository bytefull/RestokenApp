package com.example.restokenapp.api;

import com.example.restokenapp.models.Token;
import com.example.restokenapp.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiClient {
    @FormUrlEncoded
    @POST("api/v1/users/login")
    Call<Token> login(@Field("username") String email, @Field("password") String password);

    @GET("api/v1/users/me")
    Call<User> getProfile(@Header("Authorization") String fullToken);
}
