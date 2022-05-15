package com.example.nhom29_doancuoiky.fragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.CartConverter;
import com.example.nhom29_doancuoiky.response.CartApiResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CartFragment extends Fragment {


    private ListView cart;
    private ImageView img;
    private TextView totalprice;
    private Float sum;
    private Button cf_btn;
    private View vt;
    private ImageView BNT;
    private String stmp;
    private ArrayAdapter<CartApiResponse> adapter;

    private ArrayList<CartApiResponse> listitem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cart = view.findViewById(R.id.listview);
        cf_btn = view.findViewById(R.id.button);
        totalprice = view.findViewById(R.id.totalprice);

        super.onCreate(savedInstanceState);

        listitem = new ArrayList<>();
        loaddata(listitem);

        return view;
    }

    private void updatedata(Long idCart, String amount, String size, Long idProduct) {

        Map<String, String> params = new HashMap<>();
        params.put("id", idCart.toString());
        params.put("quantity", amount);
        params.put("sizeCode", size);
        params.put("productId", idProduct.toString());
        JSONObject jsonObject = new JSONObject(params);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = ApiConstant.URL_API + "cart";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                //api call success
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(), "Update cart success!", Toast.LENGTH_SHORT).show();
                        ArrayList<CartApiResponse> list = new ArrayList<>();
                        loaddata(list);
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

    public void loaddata(ArrayList<CartApiResponse> list) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = ApiConstant.URL_API + "carts/" + ApiConstant.userLog.getId();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                list.add(new CartConverter().toApiResponse(response.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setadapter(list);
                        totalprices(list);
                        if (response.length() > 0) {
                            cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Toast.makeText(getActivity(), "" + i, Toast.LENGTH_SHORT).show();
                                    //dailog(i);

                                    Dialog dialog = new Dialog(getActivity());
                                    dialog.setContentView(R.layout.custom_dailog);
                                    TextView textView = dialog.findViewById(R.id.name);

                                    ImageView imageView = dialog.findViewById(R.id.imgview);
                                    TextView amount = dialog.findViewById(R.id.amount);
                                    Button bnt1 = dialog.findViewById(R.id.bnt1);
                                    Button bnt2 = dialog.findViewById(R.id.bnt2);
                                    Button bnt3 = dialog.findViewById(R.id.bnt3);
                                    Button bnt4 = dialog.findViewById(R.id.bnt4);
                                    ImageButton bnt5 = dialog.findViewById(R.id.bnt5);
                                    RadioButton rad_tre_em = dialog.findViewById(R.id.sizete);
                                    RadioButton rad_nguoi_lon = dialog.findViewById(R.id.sizenl);
                                    RadioGroup grp = dialog.findViewById(R.id.radioGroup1);

                                    textView.setText(list.get(i).getNameProd());
                                    amount.setText(Integer.toString(list.get(i).getQuantity()));
                                    Picasso.get().load(list.get(i).getLinkImgProd()).into(imageView);
                                    String a = list.get(i).getSizeCode();
                                    switch (list.get(i).getSizeCode()) {
                                        case "tre-em": {
                                            rad_tre_em.setChecked(true);
                                            break;
                                        }
                                        case "nguoi-lon": {
                                            rad_nguoi_lon.setChecked(true);
                                            break;
                                        }
                                    }
                                    //xoa cart
                                    bnt5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //call api delete cart

                                            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                            String url = ApiConstant.URL_API + "cart/" + list.get(i).getId();

                                            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                                                    new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String response) {
                                                            Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
                                                            loaddata(new ArrayList<CartApiResponse>());
                                                        }
                                                    },
                                                    new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {
                                                            Toast.makeText(getContext(), "Fail!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                            requestQueue.add(stringRequest);
                                            dialog.cancel();
                                        }
                                    });

                                    bnt1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (Integer.parseInt((String) amount.getText()) > 1) {
                                                amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) - 1)));
                                            }

                                        }
                                    });
                                    bnt2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) + 1)));
                                        }
                                    });

                                    //confirm -> update cart
                                    bnt4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Integer check = grp.getCheckedRadioButtonId();
                                            //update cart
                                            if (rad_tre_em.isChecked()) {
                                                updatedata(list.get(i).getId(), (String) amount.getText(), "tre-em", list.get(i).getProductId());
                                            }

                                            if (rad_nguoi_lon.isChecked()) {
                                                updatedata(list.get(i).getId(), (String) amount.getText(), "nguoi-lon", list.get(i).getProductId());
                                            }

                                            dialog.cancel();

                                        }
                                    });
                                    //cancel
                                    bnt3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            dialog.cancel();

                                        }
                                    });
                                    dialog.show();

                                }
                            });

                            cf_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Fragment fragment2 = new ConfirmCartFragment();
                                    getFragmentManager().beginTransaction().
                                            replace(R.id.content_frame, fragment2).
                                            addToBackStack("frags").commit();

                                }
                            });
                        } else {
                            Fragment fragment2 = new EmptyCartFragment();
                            getFragmentManager().beginTransaction().
                                    replace(R.id.content_frame, fragment2).
                                    addToBackStack("frags").commit();
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


    private void setadapter(ArrayList<CartApiResponse> list) {
        adapter = new ArrayAdapter<CartApiResponse>(getActivity(), 0, list) {
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.cart_item, null);
                TextView name = convertView.findViewById(R.id.prd_name);
                TextView amount = convertView.findViewById(R.id.prd_amount);
                TextView price = convertView.findViewById(R.id.prd_price);
                ImageView img = convertView.findViewById(R.id.prd_img);

                CartApiResponse s = list.get(position);
                System.out.println(s.getLinkImgProd());
                Picasso.get().load(s.getLinkImgProd()).into(img);
                name.setText(s.getNameProd());
                amount.setText("Amount: " + s.getQuantity());
                price.setText("Price: " + s.getTotalMoney() + " $");

                return convertView;
            }
        };
        cart.setAdapter(adapter);
    }

    public void totalprices(ArrayList<CartApiResponse> list) {
        sum = 0F;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getTotalMoney();
        }
        totalprice.setText("Total Prices: " + sum + " $");
    }

}
