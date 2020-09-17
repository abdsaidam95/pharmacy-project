package com.example.myapplication.interfaces;

public interface HolderItemCallback<T> {

    void deleteItem(int index, T object);
}
