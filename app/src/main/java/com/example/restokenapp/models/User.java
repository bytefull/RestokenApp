package com.example.restokenapp.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    String email;

    @SerializedName("username")
    String username;

    @SerializedName("is_admin")
    Boolean isAdmin;

    @SerializedName("id")
    String uuid;

    @SerializedName("balance")
    Integer balance;

    public User(String email, String username, Boolean isAdmin, String uuid, Integer balance) {
        this.email = email;
        this.username = username;
        this.isAdmin = isAdmin;
        this.uuid = uuid;
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User {" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", isAdmin=" + isAdmin +
                ", uuid='" + uuid + '\'' +
                ", balance=" + balance +
                '}';
    }
}
