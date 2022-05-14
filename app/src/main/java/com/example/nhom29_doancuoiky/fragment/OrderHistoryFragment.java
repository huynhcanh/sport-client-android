package com.example.nhom29_doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.nhom29_doancuoiky.R;

import com.example.nhom29_doancuoiky.adapter.OrderHistoryAdapter;
import com.example.nhom29_doancuoiky.model.OrderHistory;

import java.util.ArrayList;

public class OrderHistoryFragment extends Fragment {
    View view;
    ListView lvOrderHistory;
    ArrayList<OrderHistory> listOrder;

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
        OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(getContext(), R.layout.item_order_history, listOrder);
        lvOrderHistory.setAdapter(orderHistoryAdapter);
    }

    private void addData() {
        listOrder = new ArrayList<>();
        listOrder.add(new OrderHistory(1,"17/12/2023", 500000F));
        listOrder.add(new OrderHistory(1,"16/12/2023", 500000F));
        listOrder.add(new OrderHistory(1,"15/11/2022", 500000F));
        listOrder.add(new OrderHistory(1,"14/11/2022", 500000F));
        listOrder.add(new OrderHistory(1,"13/10/2021", 500000F));
        listOrder.add(new OrderHistory(1,"12/10/2021", 500000F));
    }

    private void setControl() {
        lvOrderHistory = view.findViewById(R.id.lvOrderHistory);
    }
}
