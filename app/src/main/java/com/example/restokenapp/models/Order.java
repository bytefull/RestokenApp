package com.example.restokenapp.models;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("amount")
    private int amount;
    @SerializedName("id")
    private int id;
    @SerializedName("restaurant_id")
    private int restaurantId;

    @SerializedName("created_at")
    private String date;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Order(int amount, int id, int restaurantId, String date) {
        this.amount = amount;
        this.id = id;
        this.date = date;
        this.restaurantId = restaurantId;
    }

    public Order(int amount, int id, String date) {
        this.amount = amount;
        this.id = id;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
