package com.example.nhom29_doancuoiky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.adapter.OrderDetailAdapter;
import com.example.nhom29_doancuoiky.model.OrderDetail;

import java.util.ArrayList;

public class OrderDetailFragment extends Fragment {
    View view;
    ListView lvOrderDetail;
    ArrayList<OrderDetail> listOrder;

    Button btnOK;

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        setControl();
        setEvent();

        return view;
    }

    private void setEvent() {
        addData();
        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(getContext(), R.layout.item_order_detail, listOrder);
        lvOrderDetail.setAdapter(orderDetailAdapter);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Home.class);
                intent.putExtra("fragment_product_details",2);
                getActivity().startActivity(intent);
            }
        });
    }

    private void addData() {
        listOrder = new ArrayList<>();
        listOrder.add(new OrderDetail(1,"Ball", 1, "50000", "https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg"));
        listOrder.add(new OrderDetail(1,"asdasd", 1, "30000", "https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg"));
        listOrder.add(new OrderDetail(1,"dhgsdf", 1, "40000", "https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg"));
        listOrder.add(new OrderDetail(1,"xvcxll", 1, "50000", "https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg"));
        listOrder.add(new OrderDetail(1,"asd", 1, "60000", "https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg"));
    }

    private void setControl() {
        lvOrderDetail = view.findViewById(R.id.lvOrderDetail);
        btnOK = view.findViewById(R.id.btnOK);
    }
}
