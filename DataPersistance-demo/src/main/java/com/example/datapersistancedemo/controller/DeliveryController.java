package com.example.datapersistancedemo.controller;

import com.example.datapersistancedemo.entity.Delivery;
import com.example.datapersistancedemo.entity.RecipientAndPrice;
import com.example.datapersistancedemo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @GetMapping
    public String test(){
        return "ok";
    }

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery) {
        System.out.println(delivery);
        return deliveryService.save(delivery);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryService.getBill(deliveryId);
    }
}