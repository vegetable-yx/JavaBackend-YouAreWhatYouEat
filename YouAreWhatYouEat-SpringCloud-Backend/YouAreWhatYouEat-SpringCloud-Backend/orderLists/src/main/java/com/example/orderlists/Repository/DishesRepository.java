package com.example.orderlists.Repository;

import com.example.orderlists.Entity.DishesEntity;
import com.example.orderlists.Entity.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface DishesRepository
        extends JpaRepository<DishesEntity, BigInteger>, JpaSpecificationExecutor<DishesEntity> {


    @Query("SELECT p.dishPrice FROM DishesEntity p where p.dishId=?1")
    int getPriceById(BigInteger id);

    DishesEntity findFirstByDishId(BigInteger id);
}
