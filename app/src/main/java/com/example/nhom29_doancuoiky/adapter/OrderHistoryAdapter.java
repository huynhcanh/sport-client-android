package com.example.nhom29_doancuoiky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.model.OrderHistory;

import java.util.ArrayList;

public class OrderHistoryAdapter extends ArrayAdapter<OrderHistory> {
    Context context;
    int resource;
    ArrayList<OrderHistory> OrderHistoryModels;

    View convertView;
    ImageView ivOrderHistory;
    TextView tvOrderDate,tvTotalMoney;
    OrderHistory OrderHistory;

    public OrderHistoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OrderHistory> OrderHistoryModels) {
        super(context, resource, OrderHistoryModels);
        this.context = context;
        this.resource = resource;
        this.OrderHistoryModels = OrderHistoryModels;
    }

    @Override
    public int getCount() {
        return OrderHistoryModels.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        tvOrderDate = convertView.findViewById(R.id.tvOrderDate);
        tvTotalMoney = convertView.findViewById(R.id.tvTotalMoney);
        ivOrderHistory = convertView.findViewById(R.id.ivOrderHistory);

        OrderHistory = OrderHistoryModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvOrderDate.setText(OrderHistory.getOrderDate());
        tvTotalMoney.setText(Float.toString(OrderHistory.getTotalMoney()) + "$");

        setEvent();

        return convertView;
    }

    private void setEvent() {
        ivOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Home.class);
                intent.putExtra("fragment_OrderHistory_details",1);
                context.startActivity(intent);
            }
        });
    }
}
