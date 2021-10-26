package com.example.datapersistancedemo.repository;

import com.example.datapersistancedemo.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository // 需要添加这个注解吗？
public interface PlantRepository extends JpaRepository<Plant,Long> {
    //check if a plant by this id exists where delivery has been completed
    Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    //you can return a primitive directly
    @Query("select p.delivery.completed from Plant p where p.id = :plantId")
    Boolean deliveryCompleted(Long plantId);

//    List<Plant> findAllByPriceLessthan(BigDecimal price);
    List<Plant> findByPriceLessThan(BigDecimal price);

    Plant findByName(String name);


}
