package com.example.dishes.Repository;

import com.example.dishes.Entity.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface DishesRepository
        extends JpaRepository<DishesEntity, BigInteger>, JpaSpecificationExecutor<DishesEntity> {

    //找到所有




}
