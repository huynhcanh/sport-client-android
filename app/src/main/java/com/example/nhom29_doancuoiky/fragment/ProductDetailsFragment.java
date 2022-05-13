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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailsFragment extends Fragment {
    private ListView same;
    private  ImageView imgview;
    private  TextView nameprd;
    private  TextView priceprd;
    private  TextView makeinprd;
    private  TextView amount;
    private RadioGroup RadioGroup2;
    private Button add;
    private Button btnAdd;
    private Button btnMinus;
    private List<Product> listProduct = new ArrayList<>();
    private List<Product> same_listProduct = new ArrayList<>();
    private ArrayAdapter<Product> adapter;

    public void loadlist()
    {
        listProduct.add(new Product(1,1,1,1,10,"bong da","ball","https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg"));
        listProduct.add(new Product(2,1,1,1,20,"vot","racket","https://upload.wikimedia.org/wikipedia/commons/f/f9/Badminton-1428046.jpg"));
        listProduct.add(new Product(3,1,1,1,30,"bong ro","basketball","https://img.freepik.com/free-photo/silhouette-view-basketball-player-holding-basket-ball-black-background_155003-11454.jpg?w=2000"));
        listProduct.add(new Product(4,1,1,1,10,"xe dap","bicycle","https://i.pinimg.com/originals/b2/28/18/b22818a21d1bc2c6611ff02812360519.jpg"));
        listProduct.add(new Product(5,1,1,1,20,"bong ban","pong","https://play-lh.googleusercontent.com/Mq440gkqrlRScHfiefmWtnWbDn1lNbOoNGpfEYYy28OO3mKgnSjly3ux6RNk3BjGOA"));
        listProduct.add(new Product(6,1,1,1,30,"gang tay","boxing","https://vothuattayson.vn/wp-content/uploads/gang-tay-boxing-title.jpg"));
    }
    public void loadsamelist()
    {
        same_listProduct.add(new Product(4,1,1,1,10,"xe dap","bicycle","https://i.pinimg.com/originals/b2/28/18/b22818a21d1bc2c6611ff02812360519.jpg"));
        same_listProduct.add(new Product(5,1,1,1,20,"bong ban","pong","https://play-lh.googleusercontent.com/Mq440gkqrlRScHfiefmWtnWbDn1lNbOoNGpfEYYy28OO3mKgnSjly3ux6RNk3BjGOA"));
        same_listProduct.add(new Product(6,1,1,1,30,"gang tay","boxing","https://vothuattayson.vn/wp-content/uploads/gang-tay-boxing-title.jpg"));
    }

    private void setadapter()
    {
        adapter = new ArrayAdapter<Product>(getActivity(), 0, same_listProduct) {
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_same_product, null);
                TextView name = convertView.findViewById(R.id.prd_name);
                TextView price = convertView.findViewById(R.id.prd_price);
                ImageView img = convertView.findViewById(R.id.prd_img);
                Product s = same_listProduct.get(position);
                Picasso.get().load(s.getImage()).into(img);
                name.setText(s.getName());
                price.setText("Price: " + String.valueOf(s.getPrice()) + " $");
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

        View view= inflater.inflate(R.layout.fragment_product_details, container, false);
        imgview= view.findViewById(R.id.imgview);
        nameprd= view.findViewById(R.id.nameprd);
        priceprd= view.findViewById(R.id.priceprd);
        makeinprd= view.findViewById(R.id.makeinprd);
        amount = view.findViewById(R.id.amount);
        RadioGroup2= view.findViewById(R.id.RadioGroup2);
        add= view.findViewById(R.id.buttonadd);
        btnAdd= view.findViewById(R.id.btnAdd);
        btnMinus= view.findViewById(R.id.btnMinus);
        same=view.findViewById(R.id.same);
        RadioGroup2.check(R.id.sizem);

        // nho' lay doi tuong gui qua de set len view - thieu ham lay doi tuong gui qua
        nameprd.setText(listProduct.get(0).getName());
        priceprd.setText("PRICE: "+ Float.toString(listProduct.get(0).getPrice())+"$");
        makeinprd.setText(listProduct.get(0).getDescription());
        amount.setText("1");
        Picasso.get().load(listProduct.get(0).getImage()).into(imgview);

        setadapter();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt((String)amount.getText())>1)
                {
                    amount.setText(Integer.toString((Integer.parseInt((String) amount.getText())-1)));
                }

            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setText(Integer.toString((Integer.parseInt((String) amount.getText())+1)));
            }
        });

        same.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nameprd.setText(same_listProduct.get(i).getName());
                priceprd.setText("PRICE: " + Float.toString(same_listProduct.get(i).getPrice())+"$");
                makeinprd.setText(same_listProduct.get(i).getDescription());
                Picasso.get().load(same_listProduct.get(i).getImage()).into(imgview);
                same_listProduct.clear();
                loadsamelist();
                setadapter();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dailog_order_success);
                Button bnt= dialog.findViewById(R.id.okebnt);
                TextView title=dialog.findViewById(R.id.title);
                TextView price =dialog.findViewById(R.id.price);
                price.setText("");
                title.setText("   Product added to cart   ");
                bnt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        // gui DL sang cart o day
                    }
                });
                dialog.show();
            }
        });


        return view;
    }

}
