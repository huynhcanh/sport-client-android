package com.example.nhom29_doancuoiky.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nhom29_doancuoiky.R;


public class EmptyCartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button cf_btn;
    public EmptyCartFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static EmptyCartFragment newInstance(String param1, String param2) {
        EmptyCartFragment fragment = new EmptyCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_empty_cart, container, false);
        cf_btn= view.findViewById(R.id.button);
        cf_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cf_btn.setText("click");
                Fragment fragment2= new HomeFragment();
                getFragmentManager().beginTransaction().
                        replace(R.id.content_frame, fragment2).
                        addToBackStack("frags").commit();
            }
        });
        return view;
    }
}