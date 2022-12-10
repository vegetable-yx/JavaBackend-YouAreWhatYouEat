package com.example.orderdish.repository;

import com.example.orderdish.entity.DishorderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DishOrderListRepository extends JpaRepository<DishorderlistEntity, String>,JpaSpecificationExecutor<DishorderlistEntity>{
    List<DishorderlistEntity> findAllByOrderId(String orderId);
    DishorderlistEntity findByDishOrderId(String dishOrderId);
}
