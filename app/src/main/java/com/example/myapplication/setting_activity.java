package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.myapplication.controlers.AppController;

public class setting_activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private CardView add;

    private CardView card_about;
    private CardView card_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        changeStatusBarColor(R.color.colorAccentttt);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_activity);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("settings");
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



        findview();
    }

    public void findview() {
        imageView = findViewById(R.id.log_out);
        add = findViewById(R.id.add_ca);
        card_add = findViewById(R.id.add_ca);
        card_about = findViewById(R.id.card_about);
        card_add = findViewById(R.id.add_product);
        add.setOnClickListener(this);
        card_about.setOnClickListener(this);
        card_add.setOnClickListener(this);

        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_out:
                logout();

                break;
            case R.id.add_ca:
                Intent intent = new Intent(this, catigory_add.class);
                startActivity(intent);


                break;
            case R.id.card_about:
                Intent intents = new Intent(this, about_project.class);
                startActivity(intents);


                break;
            case R.id.add_product:
                Intent add = new Intent(this, add_product_activity.class);
                startActivity(add);


                break;


        }
    }

    public void logout() {
        AppController.getInstance().getUserPreferences().logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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