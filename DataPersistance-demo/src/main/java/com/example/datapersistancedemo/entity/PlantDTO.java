package com.example.datapersistancedemo.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import java.math.BigDecimal;

public class PlantDTO {
    @Nationalized   //国际化语言的string
    private String name;

    //精度 规模
    @Column(precision = 12,scale = 4)
    private BigDecimal price;
}
