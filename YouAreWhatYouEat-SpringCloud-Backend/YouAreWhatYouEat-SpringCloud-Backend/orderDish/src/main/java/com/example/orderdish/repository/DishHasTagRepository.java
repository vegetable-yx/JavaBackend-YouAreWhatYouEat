package com.example.orderdish.repository;

import com.example.orderdish.entity.DishHasTagEntity;
import com.example.orderdish.entity.DishesEntity;
import com.example.orderdish.entity.DishtagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface DishHasTagRepository extends JpaRepository<DishHasTagEntity, BigInteger>, JpaSpecificationExecutor<DishHasTagEntity> {
    List<DishHasTagEntity> findByDtagId(BigInteger tagId);

    List<DishHasTagEntity> findAllByDishId(BigInteger dishId);
}
