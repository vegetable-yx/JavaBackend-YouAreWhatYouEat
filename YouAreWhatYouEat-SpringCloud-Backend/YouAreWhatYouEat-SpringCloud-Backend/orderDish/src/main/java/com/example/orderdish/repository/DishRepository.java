package com.example.orderdish.repository;

import com.example.orderdish.entity.DishesEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface DishRepository extends JpaRepository<DishesEntity, BigInteger>, JpaSpecificationExecutor<DishesEntity> {
    DishesEntity findByDishId(BigInteger dishId);
}
