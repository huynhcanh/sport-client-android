package com.example.nhom29_doancuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhom29_doancuoiky.fragment.CartFragment;
import com.example.nhom29_doancuoiky.fragment.HomeFragment;
import com.example.nhom29_doancuoiky.fragment.OrderHistoryFragment;
import com.example.nhom29_doancuoiky.fragment.OtherProductFragment;
import com.example.nhom29_doancuoiky.fragment.PantFragment;
import com.example.nhom29_doancuoiky.fragment.ProductDetailsFragment;
import com.example.nhom29_doancuoiky.fragment.ProfileFragment;
import com.example.nhom29_doancuoiky.fragment.ShirtFragment;
import com.example.nhom29_doancuoiky.fragment.ShoeFragment;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_CART = 2;
    private static final int FRAGMENT_PRODUCT_DETAIL = 3;
    private static final int FRAGMENT_SHIRT = 4;
    private static final int FRAGMENT_PANT = 5;
    private static final int FRAGMENT_SHOE = 6;
    private static final int FRAGMENT_OTHER_PRODUCT = 7;
    private static final int FRAGMENT_ODER_HISTORY = 8;

    private int CURRENT_FRAGMENT = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFrament(new HomeFragment());
        navigationView.getMenu().findItem(R.id.home).setCheckable(true);

        // xu ly blank fragment khi press back
        FragmentManager fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getSupportFragmentManager().getBackStackEntryCount() == 0) finish();
            }
        });
        
        getData();

    }

    private void getData() {
        int fragmentID = getIntent().getIntExtra("fragment_product_details",0);
        if(fragmentID == 1){
            replaceFrament(new ProductDetailsFragment());
            CURRENT_FRAGMENT = FRAGMENT_PRODUCT_DETAIL;
        }
    }

    @Override //menu cho toolbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override // bat su kien select cho toolbar
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search){
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.home){
            if (CURRENT_FRAGMENT != FRAGMENT_HOME){
                replaceFrament(new HomeFragment());
                CURRENT_FRAGMENT = FRAGMENT_HOME;
            }
        }else if(id == R.id.cart){
            if (CURRENT_FRAGMENT != FRAGMENT_CART){
                replaceFrament(new CartFragment());
                CURRENT_FRAGMENT = FRAGMENT_CART;
            }
        }
        return true;
    }

    @Override // bat su kien cho navigation
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        item.setCheckable(true);
        if(id == R.id.home){
            if (CURRENT_FRAGMENT != FRAGMENT_HOME){
                replaceFrament(new HomeFragment());
                CURRENT_FRAGMENT = FRAGMENT_HOME;
            }
        }else if(id == R.id.shirt){
            if (CURRENT_FRAGMENT != FRAGMENT_SHIRT){
                replaceFrament(new ShirtFragment());
                CURRENT_FRAGMENT = FRAGMENT_SHIRT;
            }
            Toast.makeText(this, "Category Shirt", Toast.LENGTH_SHORT).show();

        } else if(id == R.id.pant){
            if (CURRENT_FRAGMENT != FRAGMENT_PANT){
                replaceFrament(new PantFragment());
                CURRENT_FRAGMENT = FRAGMENT_PANT;
            }
            Toast.makeText(this, "Pant Clicked", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.shoe){
            if (CURRENT_FRAGMENT != FRAGMENT_SHOE){
                replaceFrament(new ShoeFragment());
                CURRENT_FRAGMENT = FRAGMENT_SHOE;
            }
            Toast.makeText(this, "Shoe Clicked", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.other){
            if (CURRENT_FRAGMENT != FRAGMENT_OTHER_PRODUCT){
                replaceFrament(new OtherProductFragment());
                CURRENT_FRAGMENT = FRAGMENT_OTHER_PRODUCT;
            }
            Toast.makeText(this, "Other Clicked", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.cart) {
            if (CURRENT_FRAGMENT != FRAGMENT_CART){
                replaceFrament(new CartFragment());
                CURRENT_FRAGMENT = FRAGMENT_CART;
            }
        }
        else if(id == R.id.profile){
            if (CURRENT_FRAGMENT != FRAGMENT_PROFILE){
                replaceFrament(new ProfileFragment());
                CURRENT_FRAGMENT = FRAGMENT_PROFILE;
            }
        }else if(id == R.id.history){
            Intent intent = new Intent(this, Login.class);
            if (CURRENT_FRAGMENT != FRAGMENT_ODER_HISTORY){
                replaceFrament(new OrderHistoryFragment());
                CURRENT_FRAGMENT = FRAGMENT_ODER_HISTORY;
            }
        }
        else if(id == R.id.logout){
            Intent intent = new Intent(this, Login.class);
            (this).startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override // fix loi thoat app khi mo navigation drawer
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    private void replaceFrament(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
