package com.example.order.repository;

import com.example.order.entitiy.DishorderlistEntity;
import com.example.order.entitiy.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface DishOrderListRepository extends JpaRepository<DishorderlistEntity, String>,JpaSpecificationExecutor<DishorderlistEntity>{
    List<DishorderlistEntity> findAllByOrderId(String orderId);
}
