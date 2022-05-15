package com.example.nhom29_doancuoiky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;

import com.example.nhom29_doancuoiky.adapter.OrderHistoryAdapter;
import com.example.nhom29_doancuoiky.adapter.ProductAdapter;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.OrderConverter;
import com.example.nhom29_doancuoiky.converter.ProductConverter;
import com.example.nhom29_doancuoiky.model.OrderHistory;
import com.example.nhom29_doancuoiky.response.OrderApiResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class OrderHistoryFragment extends Fragment {
    View view;
    ListView lvOrderHistory;


    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_history, container, false);
        setControl();
        setEvent();

        return view;
    }

    private void setEvent() {
        addData();
    }

    private void addData() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = ApiConstant.URL_API + "orders?idNguoiDung="+ApiConstant.userLog.getId()+"&soDonHang=100&trang=1";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<OrderApiResponse> listOrder = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                listOrder.add(new OrderConverter().toApiResponse(response.getJSONObject(i)));
                            }
                            OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(getContext(), R.layout.item_order_history, listOrder);
                            lvOrderHistory.setAdapter(orderHistoryAdapter);

                            lvOrderHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(getActivity(), Home.class);
                                    intent.putExtra("fragment_product_details",3);
                                    intent.putExtra("idOrder",listOrder.get(i).getId());
                                    getActivity().startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
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
        lvOrderHistory = view.findViewById(R.id.lvOrderHistory);
    }

    private void replaceFrament(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
