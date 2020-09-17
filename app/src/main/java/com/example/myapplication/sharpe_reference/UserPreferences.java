package com.example.myapplication.sharpe_reference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.models.User;

public class UserPreferences {

    public static final String USER_PREF = "USER_PREF";
    public static final String USER_IS_LOGGED_ID = "is_logged_in";

    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_MOBILE = "mobile";

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("USER_PREF", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUser(User user) {
        editor.putBoolean(USER_IS_LOGGED_ID, true);
        editor.putString(USER_ID, user.getId());
        editor.putString(USER_NAME, user.getName());
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putString(USER_MOBILE, user.getMobile());
        editor.apply();
    }

    public User getUser() {
        User user = new User();
        user.setName(sharedPreferences.getString(USER_NAME, ""));
        user.setEmail(sharedPreferences.getString(USER_EMAIL, ""));
        user.setMobile(sharedPreferences.getString(USER_MOBILE, ""));
        return user;
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(USER_IS_LOGGED_ID, false);
    }

    public void logout(){
        editor.clear();
        editor.apply();
    }
}
