package com.example.nhom29_doancuoiky.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.model.Cart;

import java.util.ArrayList;
import java.util.List;


public class ConfirmCartFragment extends Fragment {
    private TextView total;
    private TextView NAME;
    private TextView PHONE;
    private Integer sum;
    private EditText ADDRES;
    private Button BNT_CONFIRM;
    private List<Cart> listitem = new ArrayList<>();
    public void loaddata()
    {
        listitem.add(new Cart("img","sp1",1,"M",1010));
        listitem.add(new Cart("img","sp2",1,"L",1002));
        listitem.add(new Cart("img","sp3",1,"L",1040));
        listitem.add(new Cart("img","sp4",1,"XL",1070));
        listitem.add(new Cart("img","sp5",1,"M",1050));
        listitem.add(new Cart("img","sp6",1,"XL",1006));
    }
    public void totalprices()
    {
        sum = 0;
        for (int i = 0; i < listitem.size(); i++) {
            sum += listitem.get(i).getTotalprice();
        }
        total.setText("Total Prices: " + String.valueOf(sum) + " $");
    }
    public ConfirmCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loaddata();
        View view = inflater.inflate(R.layout.fragment_confirm_cart, container, false);
        total= view.findViewById(R.id.total_price);
        totalprices();
        PHONE=view.findViewById(R.id.phonelable);
        NAME=view.findViewById(R.id.namelable);
        ADDRES=view.findViewById(R.id.address);
        NAME.setText("NAME: "+"Hoàng VIP PRO");
        PHONE.setText("PHONE:"+"0123456789");



        BNT_CONFIRM= view.findViewById(R.id.btn_confirm);
        BNT_CONFIRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ADDRES.getText().length()==0)
                {
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dailog_missing_address);
                    Button okebnt= dialog.findViewById(R.id.okebnt);
                    okebnt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
                else {
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dailog_order_success);
                    Button okebnt= dialog.findViewById(R.id.okebnt);
                    TextView proice =dialog.findViewById(R.id.price);
//                    proice.setText("You should prepare "+total.getText()+" to receive the goods");
                    okebnt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                            Fragment fragment2= new HomeFragment();
                            getFragmentManager().beginTransaction().
                                    replace(R.id.content_frame, fragment2).
                                    addToBackStack("frags").commit();
                        }
                    });
                    dialog.show();
                }

            }
        });


        return view;
    }
    public void dialog()
    {

    }
}
