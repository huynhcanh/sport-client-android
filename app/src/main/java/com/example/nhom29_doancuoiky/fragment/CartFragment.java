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

import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.model.Cart;
import com.example.nhom29_doancuoiky.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment  {


    private ListView cart;
    private ImageView img;
    private TextView totalprice;
    private Integer sum;
    private Button cf_btn;
    private View vt;
    private ImageView BNT;
    private String stmp;
    private ArrayAdapter<Cart> adapter;

    private List<Cart> listitem = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cart= view.findViewById(R.id.listview);
        cf_btn= view.findViewById(R.id.button);
        totalprice = view.findViewById(R.id.totalprice);

        super.onCreate(savedInstanceState);


        loaddata();
        adapter=null;
        if(listitem.isEmpty())
        {
            Fragment fragment2 = new EmptyCartFragment();
            getFragmentManager().beginTransaction().
                    replace(R.id.content_frame, fragment2).
                    addToBackStack("frags").commit();
            return view;
        }
        else {

            setadapter();
            totalprices();
            cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getActivity(), "" + i, Toast.LENGTH_SHORT).show();
                    dailog(i);
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



            return view;
        }
    }
    private void dailog(int i)
    {

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dailog);
        TextView textView= dialog.findViewById(R.id.name);

        ImageView imageView= dialog.findViewById(R.id.imgview);
        TextView amount= dialog.findViewById(R.id.amount);
        Button bnt1= dialog.findViewById(R.id.bnt1);
        Button bnt2= dialog.findViewById(R.id.bnt2);
        Button bnt3= dialog.findViewById(R.id.bnt3);
        Button bnt4= dialog.findViewById(R.id.bnt4);
        ImageButton bnt5= dialog.findViewById(R.id.bnt5);
        RadioButton ms= dialog.findViewById(R.id.sizem);
        RadioButton ls= dialog.findViewById(R.id.sizel);
        RadioButton xls= dialog.findViewById(R.id.sizexl);
        RadioGroup grp= dialog.findViewById(R.id.radioGroup1);
        textView.setText(listitem.get(i).getName());
        amount.setText(Integer.toString(listitem.get(i).getAmount()));
        Picasso.get().load(listitem.get(i).getSource_img()).into(imageView);
        switch (listitem.get(i).getSize())
        {
            case "M" :
            {
                ms.setChecked(true);
                break ;
            }
            case "L" :
            {
                ls.setChecked(true);
                break ;
            }
            case "XL" :
            {
                xls.setChecked(true);
                break ;
            }
        }
        bnt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listitem.remove(i);
                if (listitem.size()==0)
                {
                    dialog.cancel();
                    Fragment fragment2 = new EmptyCartFragment();
                    getFragmentManager().beginTransaction().
                            replace(R.id.content_frame, fragment2).
                            addToBackStack("frags").commit();

                }
                else {
                    dialog.cancel();
                    setadapter();
                    totalprices();
                }



            }
        });



        bnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt((String)amount.getText())>1)
                {
                    amount.setText(Integer.toString((Integer.parseInt((String) amount.getText())-1)));
                }

            }
        });
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setText(Integer.toString((Integer.parseInt((String) amount.getText())+1)));
            }
        });


        bnt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer check =grp.getCheckedRadioButtonId();
                if (ms.isChecked())  updatedata(i,(String) amount.getText(),"M");
                else if(ls.isChecked())  updatedata(i,(String) amount.getText(),"L");
                else if(xls.isChecked()) updatedata(i,(String) amount.getText(),"XL");

                totalprices();
                dialog.cancel();

            }
        });
        bnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.cancel();

            }
        });
        dialog.show();

    }
    private void updatedata(Integer i,String amount, String size)
    {
        listitem.get(i).setAmount(Integer.parseInt(amount));
        listitem.get(i).setTotalprice(listitem.get(i).getPrice()*Integer.parseInt((String)amount));
        listitem.get(i).setSize(size);
        setadapter();
    }
    public void loaddata()
    {
        listitem.add(new Cart("https://sc04.alicdn.com/kf/U821fdbb2bbdc42fa92cbb62d4b74d612h.jpg","bong da",1,"M",1010));
        listitem.add(new Cart("https://upload.wikimedia.org/wikipedia/commons/f/f9/Badminton-1428046.jpg","cau long",1,"L",1002));
        listitem.add(new Cart("https://img.freepik.com/free-photo/silhouette-view-basketball-player-holding-basket-ball-black-background_155003-11454.jpg?w=2000","bong ro",1,"L",1040));
        listitem.add(new Cart("https://i.pinimg.com/originals/b2/28/18/b22818a21d1bc2c6611ff02812360519.jpg","xe dap",1,"XL",1070));
        listitem.add(new Cart("https://vothuattayson.vn/wp-content/uploads/gang-tay-boxing-title.jpg","gang tay",1,"M",1050));
    }
    private void setadapter()
    {
        adapter = new ArrayAdapter<Cart>(getActivity(), 0, listitem) {
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.cart_item, null);
                TextView name = convertView.findViewById(R.id.prd_name);
                TextView amount = convertView.findViewById(R.id.prd_amount);
                TextView price = convertView.findViewById(R.id.prd_price);
                ImageView img = convertView.findViewById(R.id.prd_img);
                Cart s = listitem.get(position);
                Picasso.get().load(s.getSource_img()).into(img);
                name.setText(s.getName());
                amount.setText("Amount: " + String.valueOf(s.getAmount()));
                s.setTotalprice(s.getPrice()*s.getAmount());
                price.setText("Price: " + String.valueOf(s.getTotalprice()) + " $");



                return convertView;
            }

            ;

        };

        cart.setAdapter(adapter);

    }
    public void totalprices()
    {
        sum = 0;
        for (int i = 0; i < listitem.size(); i++) {
            sum += listitem.get(i).getTotalprice();
        }
        totalprice.setText("Total Prices: " + String.valueOf(sum) + " $");
    }

}
