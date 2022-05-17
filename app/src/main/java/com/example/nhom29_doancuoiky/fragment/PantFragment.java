package com.example.nhom29_doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.adapter.ProductAdapter;
import com.example.nhom29_doancuoiky.response.ProductApiResponse;

import java.util.ArrayList;

public class PantFragment extends Fragment {
    View view;
    GridView gvDanhSachProduct;
    ArrayList<ProductApiResponse> listProduct;
    ArrayList<ProductApiResponse> listPantProduct;

    public PantFragment() {
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
        loadData();
        ProductAdapter productAdapter = new ProductAdapter(getContext(), R.layout.item_product_layout, listPantProduct);
        gvDanhSachProduct.setAdapter(productAdapter);
    }

    private void addData() {
//        listProduct = new ArrayList<>();
//        listProduct.add(new Product(1,1,1,1,10,"bong da","ball","https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg"));
//        listProduct.add(new Product(2,2,1,1,20,"vot","racket","https://upload.wikimedia.org/wikipedia/commons/f/f9/Badminton-1428046.jpg"));
//        listProduct.add(new Product(3,3,2,1,30,"bong ro","basketball","https://img.freepik.com/free-photo/silhouette-view-basketball-player-holding-basket-ball-black-background_155003-11454.jpg?w=2000"));
//        listProduct.add(new Product(4,4,2,1,10,"xe dap","bicycle","https://i.pinimg.com/originals/b2/28/18/b22818a21d1bc2c6611ff02812360519.jpg"));
//        listProduct.add(new Product(5,5,3,1,20,"bong ban","pong","https://play-lh.googleusercontent.com/Mq440gkqrlRScHfiefmWtnWbDn1lNbOoNGpfEYYy28OO3mKgnSjly3ux6RNk3BjGOA"));
//        listProduct.add(new Product(6,6,4,1,30,"gang tay","boxing","https://vothuattayson.vn/wp-content/uploads/gang-tay-boxing-title.jpg"));
    }

    private void loadData() {
        addData();
        listPantProduct = new ArrayList<>();
//        for (Product str : listProduct) {
//            if(str.getCategory_id() == 2){
//                listPantProduct.add(str);
//            }
//        }
    }

    private void setControl() {
        gvDanhSachProduct = view.findViewById(R.id.gvDanhSachProduct);
    }

}
