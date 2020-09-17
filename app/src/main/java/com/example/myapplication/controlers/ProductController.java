package com.example.myapplication.controlers;

import com.example.myapplication.Operations;
import com.example.myapplication.models.Catigory;
import com.example.myapplication.models.Product;


import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProductController implements Operations<Product> {

    private Realm realm;

    public ProductController() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void create(Product object) {
        object.setId(generateID());
        realm.beginTransaction();
        realm.copyToRealm(object);
        realm.commitTransaction();
    }

    @Override
    public void update(Product object) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
    }
    public Product getCategory(String id) {
        return realm.where(Product.class).equalTo("id", id).findFirst();
    }

    @Override
    public void delete(int id) {
        realm.beginTransaction();
        realm.where(Product.class).equalTo("id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }

    @Override
    public RealmResults<Product> getAll() {
        return realm.where(Product.class).findAll();
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }
}
