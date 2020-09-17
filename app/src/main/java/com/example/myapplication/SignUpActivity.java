package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.controlers.AppController;
import com.example.myapplication.controlers.UserController;
import com.example.myapplication.models.User;

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener  {
    private EditText signUpName;
    private EditText signUpEmail;
    private EditText signUpMobile;
    private EditText signUpPassword;
    private Button signUp;

    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeView();
    }
    private void initializeView() {
        userController = new UserController();
        findViews();
    }

    private void findViews() {
        signUpName = (EditText) findViewById(R.id.name);
        signUpEmail = (EditText) findViewById(R.id.ems);
        signUpMobile = (EditText) findViewById(R.id.mobiles);
        signUpPassword = (EditText) findViewById(R.id.passes);
        signUp = (Button) findViewById(R.id.log_up);

        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_up:
                checkData();

                break;
        }
    }
        private void checkData () {
            if (!signUpName.getText().toString().isEmpty() &&
                    !signUpEmail.getText().toString().isEmpty() &&
                    !signUpMobile.getText().toString().isEmpty() &&
                    !signUpPassword.getText().toString().isEmpty()) {
                if (validateEmail(signUpEmail.getText().toString())) {
                    signUp();
                }
                else {
                    Toasty.error(this, getString(R.string.error_email), Toasty.LENGTH_SHORT).show();
                }
            } else {
                Toasty.error(this, getString(R.string.required_data_error), Toasty.LENGTH_SHORT).show();
            }


        }



    public static boolean validateEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    public void signUp() {
        User user = getUser();
        userController.register(user);
        AppController.getInstance().getUserPreferences().saveUser(user);
        preapreIntent();
    }

    private void preapreIntent() {
        Intent intent = new Intent(this, Category_List.class);
        startActivity(intent);
    }

    private User getUser() {
        User user = new User();
        user.setName(signUpName.getText().toString());
        user.setEmail(signUpEmail.getText().toString());
        user.setMobile(signUpMobile.getText().toString());
        user.setPassword(signUpPassword.getText().toString());
        return user;
    }

    public void hideStatusBar() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CRITICAL", "onStop: ");
        finish();
    }



}
