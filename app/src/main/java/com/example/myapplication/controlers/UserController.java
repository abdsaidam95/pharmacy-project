package com.example.myapplication.controlers;

import com.example.myapplication.models.User;

import java.util.UUID;

import io.realm.Realm;

public class UserController {

    private Realm realm;

    public UserController() {

        realm = Realm.getDefaultInstance();

    }

    public void register(User user) {
        user.setId(generateID());
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
    }

    public User login(String email, String password) {
        return realm.where(User.class).equalTo("email", email).and().equalTo("password", password).findFirst();
    }

    public void updateUser(User user){
        realm.beginTransaction();

        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }
    private String generateID() {
        return UUID.randomUUID().toString();
    }
}

