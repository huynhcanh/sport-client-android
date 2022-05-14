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
import android.widget.ImageView;
import android.widget.ListView;
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
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.ProductConverter;
import com.example.nhom29_doancuoiky.response.ProductApiResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailsFragment extends Fragment {
    private ListView same;
    private ImageView imgview;
    private TextView nameprd;
    private TextView priceprd;
    private TextView makeinprd;
    private TextView amount;
    private RadioGroup RadioGroup2;
    private Button add;
    private Button btnAdd;
    private Button btnMinus;
    private List<ProductApiResponse> listProduct = new ArrayList<>();
    private List<ProductApiResponse> same_listProduct = new ArrayList<>();
    private ArrayAdapter<ProductApiResponse> adapter;

    public void loadlist() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = ApiConstant.URL_API + "products?trang=1&soSanPham=100";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                listProduct.add(new ProductConverter().toApiResponse(response.getJSONObject(i)));
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
    }

    public void loadsamelist() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = ApiConstant.URL_API + "top3-products";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                same_listProduct.add(new ProductConverter().toApiResponse(response.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setadapter();
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

    private void setadapter() {
        adapter = new ArrayAdapter<ProductApiResponse>(getActivity(), 0, same_listProduct) {
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_same_product, null);
                TextView name = convertView.findViewById(R.id.prd_name);
                TextView price = convertView.findViewById(R.id.prd_price);
                ImageView img = convertView.findViewById(R.id.prd_img);

                ProductApiResponse s = same_listProduct.get(position);
                Picasso.get().load(s.getImages().get(0)).into(img);
                name.setText(s.getName());
                price.setText("Price: " + String.valueOf(s.getUnitPrice()) + " $");
                return convertView;
            }
        };
        same.setAdapter(adapter);
    }

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loadlist();
        loadsamelist();

        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        imgview = view.findViewById(R.id.imgview);
        nameprd = view.findViewById(R.id.nameprd);
        priceprd = view.findViewById(R.id.priceprd);
        makeinprd = view.findViewById(R.id.makeinprd);
        amount = view.findViewById(R.id.amount);
        RadioGroup2 = view.findViewById(R.id.RadioGroup2);
        add = view.findViewById(R.id.buttonadd);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnMinus = view.findViewById(R.id.btnMinus);
        same = view.findViewById(R.id.same);
        RadioGroup2.check(R.id.sizem);

        // nho' lay doi tuong gui qua de set len view - thieu ham lay doi tuong gui qua
        ProductApiResponse productApiResponse = (ProductApiResponse) getActivity().getIntent().getSerializableExtra("product_detail");
        nameprd.setText(productApiResponse.getName());
        priceprd.setText("PRICE: " + productApiResponse.getUnitPrice() + "$");
        makeinprd.setText(productApiResponse.getDescription());
        amount.setText("1");
        Picasso.get().load(productApiResponse.getImages().get(0)).into(imgview);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt((String) amount.getText()) > 1) {
                    amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) - 1)));
                }

            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) + 1)));
            }
        });

        same.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nameprd.setText(same_listProduct.get(i).getName());
                priceprd.setText("PRICE: " + same_listProduct.get(i).getUnitPrice() + "$");
                makeinprd.setText(same_listProduct.get(i).getDescription());
                Picasso.get().load(same_listProduct.get(i).getImages().get(0)).into(imgview);
                same_listProduct.clear();
                loadsamelist();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dailog_order_success);
                Button bnt = dialog.findViewById(R.id.okebnt);
                TextView title = dialog.findViewById(R.id.title);
                TextView price = dialog.findViewById(R.id.price);
                price.setText("");
                title.setText("   Product added to cart   ");
                bnt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        // gui DL sang cart o day
                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                        StringBuilder url = new StringBuilder(ApiConstant.URL_API)
                                .append("cart?")
                                .append("productId=" + productApiResponse.getId().toString())
                                .append("&sizeCode="+ "tre-em") //chỉnh lại radio (sizecode=["tre-em","nguoi-lon"];
                                .append("&userId=" + ApiConstant.userLog.getId().toString())
                                .append("&quantity=" + amount.getText());
                        System.out.println(url.toString());
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url.toString(), null,
                                //api call success
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(getContext(), "Add success!", Toast.LENGTH_SHORT).show();
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
                    }
                });
                dialog.show();
            }
        });
        return view;
    }

}
