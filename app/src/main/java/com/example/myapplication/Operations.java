package com.example.myapplication;

import io.realm.RealmResults;

public interface Operations<T> {

    void create(T object);

    void update(T object);

    void delete(int id);








    RealmResults<T> getAll();
}
