package com.example.myapplication.Oparaiion;

import io.realm.RealmResults;

public interface operations<T> {

    void create(T object);

    void update(T object);

    void delete(int id);

    RealmResults<T> getAll();
}
