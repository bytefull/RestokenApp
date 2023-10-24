package com.example.restokenapp.api;

import com.example.restokenapp.models.CreateUser;
import com.example.restokenapp.models.Token;
import com.example.restokenapp.models.GetUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiClient {
    String BASE_URL = "https://restoken.azurewebsites.net/api/v1/";

    @FormUrlEncoded
    @POST("users/login")
    Call<Token> login(@Field("username") String email, @Field("password") String password);

    @POST("users")
    Call<GetUser> signUp(@Body CreateUser user);

    @GET("users/me")
    Call<GetUser> getProfile(@Header("Authorization") String fullToken);
}
