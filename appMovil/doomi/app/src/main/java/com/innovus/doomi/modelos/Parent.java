package com.innovus.doomi.modelos;

import com.appspot.domi_app.domi.model.Producto;

import java.util.ArrayList;

/**
 * Created by Janeth Arcos on 21/02/2015.
 */
public class Parent {
    private String mTitle;
    private ArrayList<Producto> mArrayChildren;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public ArrayList<Producto> getArrayChildren() {
        return mArrayChildren;
    }

    public void setArrayChildren(ArrayList<Producto> arrayChildren) {
        mArrayChildren = arrayChildren;
    }
}