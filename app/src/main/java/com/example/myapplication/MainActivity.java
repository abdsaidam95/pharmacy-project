package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.controlers.AppController;
import com.example.myapplication.controlers.UserController;
import com.example.myapplication.models.User;
import com.example.myapplication.sharpe_reference.UserPreferences;

import es.dmoral.toasty.Toasty;

import static com.example.myapplication.R.id.log_in;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText email;
    private EditText pass;

    private Button logs;

    private TextView signIn;

    private UserController userController;
    private UserPreferences userPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
    }

    private void findViews() {


        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        logs = (Button) findViewById(log_in);

        signIn = (TextView) findViewById(R.id.sign_in);

        logs.setOnClickListener(this);

        signIn.setOnClickListener(this);
    }

    private void initializeView() {

        userController = new UserController();

        findViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in:
                getsec();
                break;
            case R.id.log_in:
                checkData();
                break;


        }


    }


    public void getsec() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


    private void checkData() {
        if (!email.getText().toString().isEmpty() &&
                !pass.getText().toString().isEmpty()) {
            if (validateEmail(email.getText().toString())) {
                login();
            } else {
                Toasty.error(this, getString((R.string.error)), Toasty.LENGTH_SHORT).show();
            }
        } else {
            Toasty.error(this, getString(R.string.required_data_error), Toasty.LENGTH_SHORT).show();
        }
    }

    //Patterns.EMAIL_ADDRESS.matcher(email).matches()its pattern of eamail with emails intered;
    public static boolean validateEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    private void login() {
        User loggedInUser = userController.login(email.getText().toString(), pass.getText().toString());

        if (loggedInUser != null) {

            AppController.getInstance().getUserPreferences().saveUser(loggedInUser);


            Intent intent = new Intent(this, Category_List.class);
            startActivity(intent);
        } else {
            Toasty.error(this, getString(R.string.login_error), Toasty.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CRITICAL", "onStop: ");
        finish();
    }

    public void hideStatusBar() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}