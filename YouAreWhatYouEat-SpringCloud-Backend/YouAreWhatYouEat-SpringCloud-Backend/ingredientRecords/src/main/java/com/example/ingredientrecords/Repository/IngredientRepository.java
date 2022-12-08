package com.example.ingredientrecords.Repository;

import com.example.ingredientrecords.Entity.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface IngredientRepository
        extends JpaRepository<IngredientsEntity, BigInteger>, JpaSpecificationExecutor<IngredientsEntity> {
}
