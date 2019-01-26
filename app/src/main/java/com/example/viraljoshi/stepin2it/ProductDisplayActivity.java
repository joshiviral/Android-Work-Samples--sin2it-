package com.example.viraljoshi.stepin2it;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viraljoshi.stepin2it.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDisplayActivity extends AppCompatActivity {

    public static String EXTRA_PRODUCT= "product";

    @BindView(R.id.img_item_name)
    ImageView img_item_name;

    @BindView(R.id.txt_item_show_name)
    TextView txt_item_show_name;

    @BindView(R.id.txt_item_show_price)
    TextView txt_item_show_price;

    @BindView(R.id.txt_item_show_web)
    TextView txt_item_show_web;

    @BindView(R.id.txt_item_show_dimensions_lenght)
    TextView txt_item_show_dimensions_lenght;

    @BindView(R.id.txt_item_show_dimensions_height)
    TextView txt_item_show_dimensions_height;

    @BindView(R.id.txt_item_show_dimensions_width)
    TextView txt_item_show_dimensions_width;

    @BindView(R.id.txt_item_show_phone)
    TextView txt_item_show_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Product product = (Product) intent.getParcelableExtra(EXTRA_PRODUCT);
        txt_item_show_name.setText(product.getName());
        txt_item_show_price.setText(product.getPrice().toString());
        txt_item_show_web.setText(product.getWeb());

        txt_item_show_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.bestbuy.ca/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        txt_item_show_dimensions_lenght.setText(product.getDimensions().getLength().toString());
        txt_item_show_dimensions_height.setText(product.getDimensions().getHeight().toString());
        txt_item_show_dimensions_width.setText(product.getDimensions().getWidth().toString());
        txt_item_show_phone.setText(product.getPhone());
        txt_item_show_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:+1 (562) 922-3425"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (ActivityCompat.checkSelfPermission(ProductDisplayActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

    }
}