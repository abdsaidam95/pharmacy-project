package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.categories.CategoryRecyclerAdapter;
import com.example.myapplication.controlers.CategoryController;
import com.example.myapplication.models.Catigory;

import java.util.ArrayList;
import java.util.List;

public class Category_List extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editText;
    private CategoryController categoryController;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;
    public List<Catigory> categories = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeStatusBarColor(R.color.colorAccentttt);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__list);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("catigories");



        setSupportActionBar(toolbar);

        initializeView();


    }

    private void findViews() {
        recyclerView = findViewById(R.id.categories_recycler_view);
        editText = findViewById(R.id.name_catigory);
    }

    private void initializeView() {
        categoryController = new CategoryController();
        categories = categoryController.getAll();
        findViews();
        initializeRecyclerView();
    }


    private void initializeRecyclerView() {


        categoryRecyclerAdapter = new CategoryRecyclerAdapter(categories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoryRecyclerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            // set drawable icon
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Intent intent = new Intent(this, setting_activity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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