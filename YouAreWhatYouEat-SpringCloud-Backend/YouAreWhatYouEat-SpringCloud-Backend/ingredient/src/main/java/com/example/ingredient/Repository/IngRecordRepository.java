package com.example.ingredient.Repository;

import com.example.ingredient.Entity.IngredientRecordEntity;
import com.example.ingredient.Entity.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface IngRecordRepository
        extends JpaRepository<IngredientRecordEntity, BigInteger>, JpaSpecificationExecutor<IngredientRecordEntity> {
    public IngredientRecordEntity findFirstByRecordId(BigInteger id);
}
