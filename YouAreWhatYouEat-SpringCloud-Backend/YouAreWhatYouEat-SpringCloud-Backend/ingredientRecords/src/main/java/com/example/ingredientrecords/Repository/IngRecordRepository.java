package com.example.ingredientrecords.Repository;

import com.example.ingredientrecords.Entity.IngredientRecordEntity;
import com.example.ingredientrecords.Entity.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface IngRecordRepository
        extends JpaRepository<IngredientRecordEntity, BigInteger>, JpaSpecificationExecutor<IngredientRecordEntity> {


    public List<IngredientRecordEntity> findAll();

}
