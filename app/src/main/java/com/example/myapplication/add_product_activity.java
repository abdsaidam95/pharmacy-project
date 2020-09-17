package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.controlers.CategoryController;
import com.example.myapplication.controlers.ProductController;
import com.example.myapplication.models.Catigory;
import com.example.myapplication.models.Product;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class add_product_activity extends AppCompatActivity implements  View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner categoriesSpinner;
    private EditText productName;
    private EditText productDescription;
    private Button saveProduct;

    private CategoryController categoryController ;
    private ProductController productController;

    private List<String> categoryNames = new ArrayList<>();
    private List<Catigory> categories = new ArrayList<>();
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeStatusBarColor(R.color.colorAccentttt);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_activity);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Add Category");
        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), setting_activity.class);
                startActivity(intent);

                //What to do on back clicked
            }
        });

        initializeView();
    }

    private void initializeView() {
        categoryController = new CategoryController();
        productController = new ProductController();

        categories = categoryController.getAll();
        setCategoryNames();

        findViews();
        initializeAddressesSpinner();
    }

    private void findViews() {
        categoriesSpinner = findViewById(R.id.categories_spinner);
        productName = findViewById(R.id.product_name);
        productDescription = findViewById(R.id.product_description);
        saveProduct = findViewById(R.id.save_product);

        saveProduct.setOnClickListener(this);
    }

    private void initializeAddressesSpinner() {
        categoriesSpinner.setSelection(0);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.item_spinner_style, categoryNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(dataAdapter);
        categoriesSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_product) {
            if (categoriesSpinner.getSelectedItemPosition() != 0 &&
                    !productName.getText().toString().isEmpty() &&
                    !productDescription.getText().toString().isEmpty()) {

                Product product = new Product();
                product.setName(productName.getText().toString());
                product.setDescription(productDescription.getText().toString());
                productController.create(product);

                Catigory category = categories.get(categoriesSpinner.getSelectedItemPosition() - 1);
                categoryController.addProduct(category, product);
                categoryController.update(category);
                Intent intent = new Intent(v.getContext(),setting_activity.class);
                startActivity(intent);
                Toasty.success(this, "Product saved successfully", Toasty.LENGTH_SHORT).show();

            } else {
                Toasty.error(this, "Please, check required data", Toasty.LENGTH_SHORT).show();
            }

        }
    }

    private void setCategoryNames() {
        categoryNames.add("Select Category");
        for (Catigory category : categories) {
            categoryNames.add(category.getName());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toasty.success(this, "Item: " + categoryNames.get(position), Toasty.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
