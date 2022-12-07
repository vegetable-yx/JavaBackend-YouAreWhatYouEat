package com.my.promotion.repository;

import com.my.promotion.entity.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.Optional;

public interface DishesRepository
        extends
        JpaRepository<DishesEntity, BigInteger>,
        JpaSpecificationExecutor<DishesEntity> {
    Optional<DishesEntity> findFirstByDishName(String dishName);
}
