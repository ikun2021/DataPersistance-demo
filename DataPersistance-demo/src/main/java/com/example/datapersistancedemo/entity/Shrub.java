package com.example.datapersistancedemo.entity;

import javax.persistence.Entity;

@Entity
public class Shrub extends Plant{
    private int heightCm; //any reasonable unit of measurement is fine
    private int widthCm;

    public Shrub(String name, double price) {
        super(name, price);
    }
}
