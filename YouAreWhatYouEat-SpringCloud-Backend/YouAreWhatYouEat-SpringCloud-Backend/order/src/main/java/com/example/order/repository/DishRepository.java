package com.example.order.repository;

import com.example.order.entitiy.DishesEntity;
import com.example.order.entitiy.DishorderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface DishRepository extends JpaRepository<DishesEntity, String>,JpaSpecificationExecutor<DishesEntity>{
    DishesEntity findByDishId(BigInteger dishId);
}
