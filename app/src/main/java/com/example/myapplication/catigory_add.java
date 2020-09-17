package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.controlers.CategoryController;
import com.example.myapplication.models.Catigory;

import es.dmoral.toasty.Toasty;

public class catigory_add extends AppCompatActivity  implements View.OnClickListener {
    private EditText categoryName;
    private Button saveCategory;

    private CategoryController categoryController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeStatusBarColor(R.color.colorAccentttt);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catigory_add);
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
        findViews();
    }

    private void findViews() {
        categoryName = findViewById(R.id.name_catigory);
        saveCategory = findViewById(R.id.bu_nn);

        saveCategory.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bu_nn) {
            saveNewCategory();
            Intent intent = new Intent(v.getContext(),setting_activity.class);
            startActivity(intent);
        }


    }
    private void saveNewCategory() {
        if (!categoryName.getText().toString().isEmpty()) {
            Catigory category = new Catigory();
            category.setName(categoryName.getText().toString());
            categoryController.create(category);

            clear();
            Toasty.success(this,"Category Created Successfully",Toasty.LENGTH_SHORT).show();
        } else {
            Toasty.error(this, "Please, enter category name", Toasty.LENGTH_SHORT).show();

        }
    }

    private void clear(){
        categoryName.setText("");
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




