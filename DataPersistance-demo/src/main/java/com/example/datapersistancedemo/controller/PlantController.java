package com.example.datapersistancedemo.controller;

import com.example.datapersistancedemo.entity.Plant;
import com.example.datapersistancedemo.entity.PlantDTO;
import com.example.datapersistancedemo.entity.PublicViews;
import com.example.datapersistancedemo.service.PlantService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;

    public PlantDTO getPlantDTO(String name){
        Plant plant = plantService.findByName(name);
        return convertPlantToPlantDTO(plant);
    }

    @JsonView(PublicViews.class)
    public Plant getFilteredPlant(String name){
        return plantService.findByName(name);
    }

    private static PlantDTO convertPlantToPlantDTO(Plant plant){
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant,plantDTO);
        return plantDTO;
    }

    @GetMapping("/under-price/{price}")
    @JsonView(PublicViews.class)
    public List<Plant> plantsCheaperThan(@PathVariable BigDecimal price) {
        return plantService.findPlantsBelowPrice(price);
    }


}