package com.example.datapersistancedemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


public class CandyData {
    private Long id;
    private String name;
    private BigDecimal price;
}
