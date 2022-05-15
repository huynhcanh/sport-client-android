package com.example.nhom29_doancuoiky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.model.OrderHistory;
import com.example.nhom29_doancuoiky.response.OrderApiResponse;

import java.util.ArrayList;

public class OrderHistoryAdapter extends ArrayAdapter<OrderApiResponse> {
    Context context;
    int resource;
    ArrayList<OrderApiResponse> OrderHistoryModels;
    OrderApiResponse orderHistory;

    ImageView ivOrderHistory;
    TextView tvOrderDate,tvTotalMoney,tvStatus;


    public OrderHistoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OrderApiResponse> OrderHistoryModels) {
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
        tvStatus = convertView.findViewById(R.id.tvStatus);
        ivOrderHistory = convertView.findViewById(R.id.ivOrderHistory);

        orderHistory = OrderHistoryModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvOrderDate.setText(orderHistory.getDateCreate());
        tvTotalMoney.setText(Float.toString(orderHistory.getTotalMoney()) + "$");
        tvStatus.setText("Đã xác nhận");

        return convertView;
    }
}
