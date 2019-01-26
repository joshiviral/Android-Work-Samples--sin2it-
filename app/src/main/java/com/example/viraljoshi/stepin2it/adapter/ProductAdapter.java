package com.example.viraljoshi.stepin2it.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viraljoshi.stepin2it.R;
import com.example.viraljoshi.stepin2it.api.CustomItemInterface;
import com.example.viraljoshi.stepin2it.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context context;
    private ArrayList<Product> productArrayList;
    CustomItemInterface listener;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList, CustomItemInterface listener) {
        this.context = context;
        this.productArrayList = productArrayList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.cell_product, parent, false);


        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {

        final Product product = productArrayList.get(position);
        holder.txt_product_name.setText("Name=" + product.getName());
        holder.txt_product_price.setText("Price=" + product.getPrice());
        Timber.i("Product=" + product.getProductId());
        Timber.i("Product=" + product.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(product,position);
            }
        });


    }

    @Override
    public int getItemCount() {

        return productArrayList.size();
    }


    class ProductViewHolder extends ViewHolder {
        @BindView(R.id.txt_product_name)
        public TextView txt_product_name;
        @BindView(R.id.txt_product_price)
        public TextView txt_product_price;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}