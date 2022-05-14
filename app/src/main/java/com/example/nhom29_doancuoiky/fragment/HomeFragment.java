package com.example.nhom29_doancuoiky.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.adapter.ProductAdapter;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.ProductConverter;
import com.example.nhom29_doancuoiky.converter.UserConverter;
import com.example.nhom29_doancuoiky.model.Product;
import com.example.nhom29_doancuoiky.response.ProductApiResponse;
import com.example.nhom29_doancuoiky.response.UserApiResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    View view;
    GridView gvDanhSachProduct;
    ArrayList<Product> listProduct;
    ArrayList<ProductApiResponse> listProductApiResponse;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setControl();
        setEvent();

        return view;
    }

    private void setEvent() {
        addData();
//        ProductAdapter productAdapter = new ProductAdapter(getContext(), R.layout.item_product_layout, listProduct);
//        gvDanhSachProduct.setAdapter(productAdapter);
    }

    private void addData() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = ApiConstant.URL_API + "products?trang=1&soSanPham=100";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                listProductApiResponse.add(new ProductConverter().toApiResponse(response.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Danh sách hiện không thể tải !", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

//        listProduct = new ArrayList<>();
//        listProduct.add(new Product(1,1,1,1,10,"bong da","ball","https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg"));
//        listProduct.add(new Product(2,2,2,1,20,"vot","racket","https://upload.wikimedia.org/wikipedia/commons/f/f9/Badminton-1428046.jpg"));
//        listProduct.add(new Product(3,3,1,1,30,"bong ro","basketball","https://img.freepik.com/free-photo/silhouette-view-basketball-player-holding-basket-ball-black-background_155003-11454.jpg?w=2000"));
//        listProduct.add(new Product(4,4,2,1,10,"xe dap","bicycle","https://i.pinimg.com/originals/b2/28/18/b22818a21d1bc2c6611ff02812360519.jpg"));
//        listProduct.add(new Product(5,5,1,1,20,"bong ban","pong","https://play-lh.googleusercontent.com/Mq440gkqrlRScHfiefmWtnWbDn1lNbOoNGpfEYYy28OO3mKgnSjly3ux6RNk3BjGOA"));
//        listProduct.add(new Product(6,6,2,1,30,"gang tay","boxing","https://vothuattayson.vn/wp-content/uploads/gang-tay-boxing-title.jpg"));
    }

    private void setControl() {
        gvDanhSachProduct = view.findViewById(R.id.gvDanhSachProduct);
    }

}
