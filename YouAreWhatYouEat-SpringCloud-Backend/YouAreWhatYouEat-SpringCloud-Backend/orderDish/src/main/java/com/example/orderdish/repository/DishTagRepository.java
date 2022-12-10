package com.example.orderdish.repository;

import com.example.orderdish.entity.DishesEntity;
import com.example.orderdish.entity.DishtagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface DishTagRepository extends JpaRepository<DishtagsEntity, BigInteger>, JpaSpecificationExecutor<DishtagsEntity> {
    DishtagsEntity findByDtagName(String tagName);
    DishtagsEntity findByDtagId(BigInteger dTagId);
}
