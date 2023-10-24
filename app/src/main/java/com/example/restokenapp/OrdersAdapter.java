package com.example.restokenapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restokenapp.models.Order;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    ArrayList<Order> data = new ArrayList<>();

    public OrdersAdapter(ArrayList<Order> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.amount.setText(String.valueOf(data.get(position).getAmount()));
        holder.id.setText(String.valueOf(data.get(position).getId()));
        holder.date.setText(String.valueOf(data.get(position).getDate()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amount, id, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.item_amount);
            id = itemView.findViewById(R.id.item_id);
            date = itemView.findViewById(R.id.item_date);
        }
    }
}