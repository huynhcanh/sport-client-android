package com.example.nhom29_doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.adapter.ProductAdapter;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.ProductConverter;
import com.example.nhom29_doancuoiky.model.Product;
import com.example.nhom29_doancuoiky.response.ProductApiResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    View view;
    GridView gvDanhSachProduct;
    ArrayList<ProductApiResponse> listProduct;
    ArrayList<ProductApiResponse> listProductApiResponse = new ArrayList<>();

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
                            ProductAdapter productAdapter = new ProductAdapter(getContext(), R.layout.item_product_layout, listProductApiResponse);
                            gvDanhSachProduct.setAdapter(productAdapter);
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
    }

    private void setControl() {
        gvDanhSachProduct = view.findViewById(R.id.gvDanhSachProduct);
    }

}
