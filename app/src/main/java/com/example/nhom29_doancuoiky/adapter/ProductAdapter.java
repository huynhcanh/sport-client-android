package com.example.nhom29_doancuoiky.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.fragment.CartFragment;
import com.example.nhom29_doancuoiky.fragment.EmptyCartFragment;
import com.example.nhom29_doancuoiky.model.Cart;
import com.example.nhom29_doancuoiky.model.Product;
import com.example.nhom29_doancuoiky.model.ProductSize;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    int resource;
    ArrayList<Product> productModels;

    View convertView;
    ImageView ivImage;
    TextView tvName,tvPrice;
    FloatingActionButton fbAddToCart;
    Product product;
    ProductSize productSize;
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

        setControl();
        product = productModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
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
                Intent intent = new Intent(context, Home.class);
                intent.putExtra("fragment_product_details",1);
                context.startActivity(intent);
            }
        });

        fbAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                productSize = new ProductSize(1,product.getId(),1,1);
//                Intent intent = new Intent(context, CartFragment.class);
//                intent.putExtra("product_add_to_cart",productSize);
//                context.startActivity(intent);
//                Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();

                // gui du lieu sang cart o day

            }
        });
    }

}
