package com.example.datapersistancedemo.entity;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQuery(name = "Delivery.findByName",
        query = "select d from Delivery d where d.name = :name")
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized  //国际化语言的string
    private String name;

    @Column(name = "address_full" ,length = 500)
    private String address;

    @Type(type="yes_no")
    private Boolean completed = false; // 初始值为false

    //不包含时区，同时有时期和时间
    private LocalDateTime deliveryTime;
    //Modify Delivery so that if it is removed, it will also remove any Plants associated with it at the same time.
    @OneToMany(mappedBy = "delivery",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Plant> plants;

    public Delivery(String name, String address, LocalDateTime deliveryTime) {
        this.name = name;
        this.address = address;
        this.deliveryTime = deliveryTime;
    }

    public Delivery() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return name;
    }

    public void setRecipientName(String recipientName) {
        this.name = recipientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
