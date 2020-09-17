package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.adapters.product.products.ProductsRecyclerAdapter;
import com.example.myapplication.controlers.CategoryController;
import com.example.myapplication.controlers.ProductController;
import com.example.myapplication.models.Catigory;
import com.example.myapplication.models.Product;

import java.util.ArrayList;
import java.util.List;

public class product_catigoy extends AppCompatActivity {
    private RecyclerView productsRecyclerView;

    private ProductsRecyclerAdapter productsRecyclerAdapter;
    private List<Product> products = new ArrayList<>();

    private String categoryId;
    private CategoryController categoryController;
    private ProductController productController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeStatusBarColor(R.color.colorAccentttt);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_catigoy);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("product");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Category_List.class);
                startActivity(intent);

                //What to do on back clicked
            }
        });
        initializeView();
    }

    private void initializeView() {
        categoryController = new CategoryController();
        productController = new ProductController();
        categoryId = getIntent().getExtras().getString("categoryId");

        findViews();
        getCategory();
        initializeRecyclerAdatper();
    }

    private void getCategory() {
        Catigory category = categoryController.getCategory(categoryId);
        if (category != null) {
            products = category.getProducts();
            Toast.makeText(this, "COUNT: " + products.size(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "NULL", Toast.LENGTH_SHORT).show();
        }

    }

    private void findViews() {
        productsRecyclerView = findViewById(R.id.categories_recycler_view);
    }

    private void initializeRecyclerAdatper() {
        productsRecyclerAdapter = new ProductsRecyclerAdapter(products);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        productsRecyclerView.setLayoutManager(layoutManager);
        productsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        productsRecyclerView.setAdapter(productsRecyclerAdapter);
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
