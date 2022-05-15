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
import com.example.nhom29_doancuoiky.model.OrderDetail;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class OrderDetailAdapter extends ArrayAdapter<OrderDetail> {
    Context context;
    int resource;
    ArrayList<OrderDetail> OrderDetailModels;
    OrderDetail orderDetail;

    ImageView ivImage;
    TextView tvName,tvAmount,tvPrice;

    public OrderDetailAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OrderDetail> OrderDetailModels) {
        super(context, resource, OrderDetailModels);
        this.context = context;
        this.resource = resource;
        this.OrderDetailModels = OrderDetailModels;
    }

    @Override
    public int getCount() {
        return OrderDetailModels.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        ivImage = convertView.findViewById(R.id.ivImage);
        tvName = convertView.findViewById(R.id.tvName);
        tvAmount = convertView.findViewById(R.id.tvAmount);
        tvPrice = convertView.findViewById(R.id.tvPrice);

        orderDetail = OrderDetailModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvName.setText("Name:        " + orderDetail.getName());
        tvAmount.setText("Amount:    " + orderDetail.getAmount());
        tvPrice.setText("Price:         " + orderDetail.getPrice() + "$");
        Picasso.get().load(orderDetail.getImage()).into(ivImage);

//        setEvent();

        return convertView;
    }

//    private void setEvent() {
//        ivImage.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent(context, Home.class);
//                intent.putExtra("fragment_product_details",3);
//                context.startActivity(intent);
//            }
//        });
//    }
}
