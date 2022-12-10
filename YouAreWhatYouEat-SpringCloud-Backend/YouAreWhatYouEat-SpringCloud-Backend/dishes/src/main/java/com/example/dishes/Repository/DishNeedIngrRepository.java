package com.example.dishes.Repository;

import com.example.dishes.Entity.DisheNeedIngrEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface DishNeedIngrRepository
        extends JpaRepository<DisheNeedIngrEntity, BigInteger>, JpaSpecificationExecutor<DisheNeedIngrEntity> {




    @Transactional
    void deleteByDishId(BigInteger id);
}
