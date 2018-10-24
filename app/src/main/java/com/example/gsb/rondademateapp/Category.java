package com.example.gsb.rondademateapp;

import android.graphics.drawable.Drawable;


public class Category {

    private String nombre;

    public Category() {
        super();
    }

    public Category(String nombre) {
        super();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}