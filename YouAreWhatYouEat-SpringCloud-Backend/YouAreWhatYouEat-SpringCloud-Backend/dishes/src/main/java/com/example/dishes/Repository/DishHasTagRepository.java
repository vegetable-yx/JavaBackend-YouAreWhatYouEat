package com.example.dishes.Repository;

import com.example.dishes.Entity.DishHasTagEntity;
import com.example.dishes.Entity.DishesEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface DishHasTagRepository
        extends JpaRepository<DishHasTagEntity, BigInteger>, JpaSpecificationExecutor<DishHasTagEntity> {

    @Transactional
    @Query("delete from DishHasTagEntity where dishId=?1 ")
    void deleteByDishId(BigInteger id);
}
