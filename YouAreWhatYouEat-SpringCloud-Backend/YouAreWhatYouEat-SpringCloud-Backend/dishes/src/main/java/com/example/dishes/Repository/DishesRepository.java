package com.example.dishes.Repository;

import com.example.dishes.Entity.DishHasTagEntity;
import com.example.dishes.Entity.DishesEntity;
import com.example.dishes.Entity.DishtagsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DishesRepository
        extends JpaRepository<DishesEntity, BigInteger>, JpaSpecificationExecutor<DishesEntity> {

    //找到所有

    public List<DishesEntity> findAll();



    @Transactional
    void deleteByDishId(BigInteger id);

    Collection<DishesEntity> findFirstByDishId(BigInteger id);




}
