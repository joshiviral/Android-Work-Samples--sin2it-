package com.example.viraljoshi.stepin2it.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.viraljoshi.stepin2it.fragments.ProductDetailFragment;
import com.example.viraljoshi.stepin2it.fragments.ProductImageFragment;
import com.example.viraljoshi.stepin2it.fragments.ProductMapFragment;
import com.example.viraljoshi.stepin2it.model.Product;

public class ProductViewPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private Product product;

    public ProductViewPagerAdapter(FragmentManager fragmentManager, Product product) {
        super(fragmentManager);


        this.product = product;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return ProductDetailFragment.newInstance(product);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return ProductImageFragment.newInstance(product);
            case 2: // Fragment # 1 - This will show SecondFragment
                return ProductMapFragment.newInstance(product);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return "detail";
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return "image";
            case 2: // Fragment # 1 - This will show SecondFragment
                return "map";
            default:
                return null;
        }

    }

}
