package com.example.datapersistancedemo;

import com.example.datapersistancedemo.entity.Delivery;
import com.example.datapersistancedemo.entity.Plant;
import com.example.datapersistancedemo.repository.PlantRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class PlantRepositoryTest {
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testPriceLessThan(){
        Plant p = testEntityManager.persist(new Plant("Foo Leaf", 4.99));
        testEntityManager.persist(new Plant("Bar Weed", 5.01));
        List<Plant> cheapPlants = plantRepository.findByPriceLessThan(BigDecimal.valueOf(5));
        Assertions.assertEquals(1, cheapPlants.size());
        Assertions.assertEquals(p.getId(), cheapPlants.get(0).getId());
    }

    @Test
    public void testDeliveryCompleted(){
        Plant p = testEntityManager.persist(new Plant("Baz Root", 9.99));
        Delivery d = testEntityManager.persist(new Delivery("Leonard Bernstein", "234 West Side", LocalDateTime.now()));

        d.setPlants(Lists.newArrayList(p));
        p.setDelivery(d);

        //test both before and after
        Assertions.assertFalse(plantRepository.deliveryCompleted(p.getId())); //assertFalse会报错，除非给completed一个初始值false
        d.setCompleted(true);
        Assertions.assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }
}
