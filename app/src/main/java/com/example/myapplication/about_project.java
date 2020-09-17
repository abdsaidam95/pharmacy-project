package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class about_project extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        changeStatusBarColor(R.color.colorAccentttt);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_project);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("About");



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
