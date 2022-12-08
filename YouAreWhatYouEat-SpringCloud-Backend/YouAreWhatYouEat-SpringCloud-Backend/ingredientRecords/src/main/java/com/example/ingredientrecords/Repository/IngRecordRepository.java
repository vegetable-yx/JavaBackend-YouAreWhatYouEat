package com.example.ingredientrecords.Repository;

import com.example.ingredientrecords.Entity.IngredientRecordEntity;

import java.util.List;

public interface IngRecordRepository {


    public List<IngredientRecordEntity> findAll();

}
