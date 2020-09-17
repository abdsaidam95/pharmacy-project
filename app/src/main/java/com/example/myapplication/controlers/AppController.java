package com.example.myapplication.controlers;

import android.app.Application;


import com.example.myapplication.sharpe_reference.UserPreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class AppController extends Application {

    public static AppController instance;

    private UserPreferences userPreferences;

    public static AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


        userPreferences = new UserPreferences(this);






        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config =
                new RealmConfiguration.Builder()
                        .name("test.db")
                        .schemaVersion(1)

              .deleteRealmIfMigrationNeeded()

                        .build();
//        Realm.getInstance(config);


    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }
}
