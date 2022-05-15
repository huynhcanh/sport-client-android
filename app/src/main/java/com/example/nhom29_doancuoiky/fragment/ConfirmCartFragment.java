package com.example.nhom29_doancuoiky.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.CartConverter;
import com.example.nhom29_doancuoiky.response.CartApiResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ConfirmCartFragment extends Fragment {
    private TextView total;
    private TextView NAME;
    private TextView PHONE;
    private float sum;
    private EditText ADDRES;
    private Button BNT_CONFIRM;
    private List<CartApiResponse> listitem;

    public void loaddata() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = ApiConstant.URL_API + "carts/" + ApiConstant.userLog.getId();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            listitem = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                listitem.add(new CartConverter().toApiResponse(response.getJSONObject(i)));
                            }
                            totalprices(listitem);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), "Bạn chưa thêm sản phẩm nào vào giỏ!", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void totalprices(List<CartApiResponse> listitem) {
        sum = 0F;
        for (int i = 0; i < listitem.size(); i++) {
            sum += listitem.get(i).getTotalMoney();
        }
        total.setText("Total Prices: " + String.valueOf(sum) + " $");
    }

    public ConfirmCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listitem = new ArrayList<>();
        // Inflate the layout for this fragment
        loaddata();
        View view = inflater.inflate(R.layout.fragment_confirm_cart, container, false);
        total = view.findViewById(R.id.total_price);
        PHONE = view.findViewById(R.id.phonelable);
        NAME = view.findViewById(R.id.namelable);
        ADDRES = view.findViewById(R.id.address);
        NAME.setText("NAME: " + ApiConstant.userLog.getName());
        PHONE.setText("PHONE:" + ApiConstant.userLog.getPhone());


        BNT_CONFIRM = view.findViewById(R.id.btn_confirm);
        BNT_CONFIRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ADDRES.getText().length() == 0) {
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dailog_missing_address);
                    Button okebnt = dialog.findViewById(R.id.okebnt);
                    okebnt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                } else {

                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dailog_order_success);
                    Button okebnt = dialog.findViewById(R.id.okebnt);
                    TextView proice = dialog.findViewById(R.id.price);
//                    proice.setText("You should prepare "+total.getText()+" to receive the goods");
                    okebnt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                            StringBuilder url = new StringBuilder(ApiConstant.URL_API)
                                    .append("order?")
                                    .append("userId=" + ApiConstant.userLog.getId().toString())
                                    .append("&adddress=" + ADDRES.getText().toString());

                            System.out.println(url.toString());
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url.toString(), null,
                                    //api call success
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Toast.makeText(getContext(), "Order success!", Toast.LENGTH_SHORT).show();
                                            Fragment fragment2 = new HomeFragment();
                                            getFragmentManager().beginTransaction().
                                                    replace(R.id.content_frame, fragment2).
                                                    addToBackStack("frags").commit();
                                        }
                                    },
                                    //api call fail
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(getContext(), "Sản phẩm đã thêm hoặc chưa có hàng loại đó !", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            requestQueue.add(jsonObjectRequest);
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }

            }
        });

        return view;
    }

    public void dialog() {

    }
}
