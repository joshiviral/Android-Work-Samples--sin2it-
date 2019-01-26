package com.example.viraljoshi.stepin2it.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viraljoshi.stepin2it.R;
import com.example.viraljoshi.stepin2it.adapter.ProductViewPagerAdapter;
import com.example.viraljoshi.stepin2it.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDisplayActivity extends AppCompatActivity {

    public static String EXTRA_PRODUCT= "product";

    @BindView(R.id.vpPager)
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Product product = (Product) intent.getParcelableExtra(EXTRA_PRODUCT);

        ProductViewPagerAdapter productViewPagerAdapter = new ProductViewPagerAdapter(getSupportFragmentManager(),product);
        viewPager.setAdapter(productViewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(ProductDisplayActivity.this,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });


    }
}