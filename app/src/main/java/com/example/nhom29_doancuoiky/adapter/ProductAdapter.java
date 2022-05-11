package com.example.nhom29_doancuoiky.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    int resource;
    ArrayList<Product> productModels;

    View convertView;
    ImageView ivImage;
    TextView tvName,tvPrice;
    FloatingActionButton fbAddToCart;
    public ProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> productModels) {
        super(context, resource, productModels);
        this.context = context;
        this.resource = resource;
        this.productModels = productModels;
    }

    @Override
    public int getCount() {
        return productModels.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        ivImage = convertView.findViewById(R.id.ivProductImage);
        tvName = convertView.findViewById(R.id.tvProductName);
        tvPrice = convertView.findViewById(R.id.tvProductPrice);
        fbAddToCart = convertView.findViewById(R.id.fbAddToCart);

        //setControl();
        Product product = productModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvName.setText(product.getName());
        tvPrice.setText(Float.toString(product.getPrice()) + "$");
        Picasso.get().load(product.getImage()).into(ivImage);

        setEvent();

        return convertView;
    }

    private void setControl() {
    }

    private void setEvent() {
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, MusicianDetail.class);
//                intent.putExtra("item", musician); // gửi 1 đối tượng qua intent
//                (context).startActivity(intent);
                Toast.makeText(context, "chi tiet sp", Toast.LENGTH_SHORT).show();
            }
        });

        fbAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
