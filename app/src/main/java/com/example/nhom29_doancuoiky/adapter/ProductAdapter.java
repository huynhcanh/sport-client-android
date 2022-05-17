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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.ProductConverter;
import com.example.nhom29_doancuoiky.model.ProductSize;
import com.example.nhom29_doancuoiky.response.ProductApiResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;


public class ProductAdapter extends ArrayAdapter<ProductApiResponse> {
    Context context;
    int resource;
    ArrayList<ProductApiResponse> productModels;

    View convertView;
    ImageView ivImage;
    TextView tvName, tvPrice, tvDiscount, tvSalePrice;
    FloatingActionButton fbAddToCart;
    ProductApiResponse productApiResponse;
    ProductSize productSize;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ProductApiResponse> productModels) {
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
        convertView = LayoutInflater.from(context).inflate(resource, null);
        setControl(convertView);
        productApiResponse = productModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvName.setText(productApiResponse.getName());
        tvPrice.setText(Float.toString(productApiResponse.getUnitPrice()) + "$");
        tvDiscount.setText("Sale"+Float.toString(productApiResponse.getDiscount()) + "%");
        tvSalePrice.setText(Float.toString(productApiResponse.getSalePrice()) + "$");
        Picasso.get().load(productApiResponse.getImages().get(0)).into(ivImage);
        setEvent(position);
        return convertView;
    }

    private void setControl(View convertView) {
        ivImage = convertView.findViewById(R.id.ivProductImage);
        tvName = convertView.findViewById(R.id.tvProductName);
        tvPrice = convertView.findViewById(R.id.tvProductPrice);
        tvDiscount = convertView.findViewById(R.id.tvDiscount);
        tvSalePrice = convertView.findViewById(R.id.tvSalePrice);
        fbAddToCart = convertView.findViewById(R.id.fbAddToCart);
    }

    private void setEvent(int position) {
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                String url = ApiConstant.URL_API + "/product?id=" + productModels.get(position).getId();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        //api call success
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                productApiResponse = new ProductConverter().toApiResponse(response);
                                Intent intent = new Intent(getContext(), Home.class);
                                intent.putExtra("product_detail", productApiResponse);
                                intent.putExtra("fragment_product_details", 1);
                                context.startActivity(intent);
                            }
                        },
                        //api call fail
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Ops! Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });

        fbAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gui du lieu sang cart o day
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                String url = ApiConstant.URL_API + "/product?id=" + productModels.get(position).getId();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        //api call success
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                productApiResponse = new ProductConverter().toApiResponse(response);

                                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                StringBuilder url = new StringBuilder(ApiConstant.URL_API)
                                        .append("cart?")
                                        .append("productId=" + productApiResponse.getId().toString())
                                        .append("&sizeCode=nguoi-lon") // auto nguoi lon
                                        .append("&userId=" + ApiConstant.userLog.getId().toString())
                                        .append("&quantity=1");
                                System.out.println(url.toString());
                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url.toString(), null,
                                        //api call success
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Toast.makeText(getContext(), "Add success!", Toast.LENGTH_SHORT).show();
                                            }
                                        },
                                        //api call fail
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getContext(), "Sản phẩm đã thêm hoặc chưa có hàng loại đó", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                requestQueue.add(jsonObjectRequest);
                            }
                        },
                        //api call fail
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Ops! Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }

}
