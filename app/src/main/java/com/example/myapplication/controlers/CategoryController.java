package com.example.myapplication.controlers;

import com.example.myapplication.Operations;
import com.example.myapplication.models.Catigory;
import com.example.myapplication.models.Product;


import java.util.Locale;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class CategoryController implements Operations<Catigory> {

    private Realm realm;

    public CategoryController() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void create(Catigory object) {
        object.setId(generateID());
        realm.beginTransaction();
        realm.copyToRealm(object);
        realm.commitTransaction();
    }

    @Override
    public void update(Catigory object) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
    }

    @Override
    public void delete(int id) {
        realm.beginTransaction();
        realm.where(Catigory.class).equalTo("id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }
    public Catigory getCategory(String id) {
        return realm.where(Catigory.class).equalTo("id", id).findFirst();
    }
    public void addProduct(Catigory catigory, Product product){
        realm.beginTransaction();
        catigory.getProducts().add(product);
        realm.commitTransaction();
    }

    @Override
    public RealmResults<Catigory> getAll() {
        return realm.where(Catigory.class).findAll();
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }
}
