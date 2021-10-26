package com.example.datapersistancedemo.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;


@Entity
public class Flower extends Plant{
    private String color;


    public Flower(String name, double price) {
        super(name, price);
    }

    public Flower() {

    }
}
