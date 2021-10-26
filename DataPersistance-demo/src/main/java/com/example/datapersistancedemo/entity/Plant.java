package com.example.datapersistancedemo.entity;


import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
// Uses InheritanceType.JOINED to store shared fields in 'plant' and unique fields in subclass tables
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
    @Id
    @GeneratedValue
    private Long id;

    @JsonView(PublicViews.class)
    @Nationalized   //国际化语言的string
    private String name;

    //精度 规模
    @JsonView(PublicViews.class)
    @Column(precision = 12,scale = 4)
    private BigDecimal price; //BigDecimal is the standard Java class for currency math

    @ManyToOne(fetch = FetchType.LAZY) //it will only query for Delivery objects when they are referenced
    @JoinColumn(name = "delivery_id") //map the join column in the plant table
    private Delivery delivery;

    public Plant(String name, double price){
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public Plant() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
