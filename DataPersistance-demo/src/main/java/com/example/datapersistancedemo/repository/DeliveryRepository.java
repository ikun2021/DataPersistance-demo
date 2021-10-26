package com.example.datapersistancedemo.repository;


import com.example.datapersistancedemo.entity.Delivery;
import com.example.datapersistancedemo.entity.Plant;
import com.example.datapersistancedemo.entity.RecipientAndPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional  //spring事务管理机制
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;
    

    public void persist(Delivery delivery){

        entityManager.persist(delivery);
    }

    public Delivery find(Long id){
        return entityManager.find(Delivery.class,id);
    }

    public Delivery merge(Delivery delivery){
        return entityManager.merge(delivery);
    }

    public void delete(Long id){
        Delivery delivery = entityManager.find(Delivery.class,id);
        entityManager.remove(delivery);
    }

    public List<Delivery> findByName(String name){
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName",Delivery.class);
        query.setParameter("name",name);
        return query.getResultList();
    }


    //拿到delivery，计算所有plant的price之和
    public RecipientAndPrice getBill(Long deliveryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
        Root<Plant> root = query.from(Plant.class);
        query.select(//创建query
                        cb.construct(
                                RecipientAndPrice.class, //类名
                                root.get("delivery").get("name"),  //name属性
                                cb.sum(root.get("price"))))    //root节点拿到price，再求和
                .where(cb.equal(root.get("delivery").get("id"), deliveryId));  // where语句
        return entityManager.createQuery(query).getSingleResult();
    }
}
