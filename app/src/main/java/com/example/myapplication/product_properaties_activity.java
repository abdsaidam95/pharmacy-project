package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.controlers.CategoryController;
import com.example.myapplication.controlers.ProductController;
import com.example.myapplication.models.Catigory;
import com.example.myapplication.models.Product;

import java.util.ArrayList;
import java.util.List;

public class product_properaties_activity extends AppCompatActivity {
    private TextView name;
    private TextView discription;
    private Product product;
    private ProductController productController;
    private   String productId ;
   private  Catigory catigory;
    int position ;

    public List<Product> catigories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeStatusBarColor(R.color.colorAccentttt);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_properaties_activity);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("About");



        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), product_catigoy.class);
                startActivity(intent);

                //What to do on back clicked
            }
        });
        findview();
        getdata();
    }
    public void findview (){
        name =findViewById(R.id.nname);
        discription =findViewById(R.id.ddiscription);
        productController =new ProductController();

    } public void getdata ( ){
        productId = getIntent().getExtras().getString("productId");
        Product product = productController.getCategory(productId);
    name.setText(product.getName().toString());
    discription.setText(product.getDescription().toString());














    }
    public void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(color, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(color));
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CRITICAL", "onStop: ");
        finish();
    }




}
