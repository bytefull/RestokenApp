package com.example.restokenapp.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("access_token")
    String accessToken;

    @SerializedName("token_type")
    String tokenType;

    public Token(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }


    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getFullToken() {
        return this.tokenType + " " + this.accessToken;
    }

    @NonNull
    @Override
    public String toString() {
        return "Token {" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
