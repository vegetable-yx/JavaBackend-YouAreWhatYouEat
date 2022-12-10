package com.example.ingredient.Repository;

import com.example.ingredient.Entity.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.text.Bidi;

public interface IngRepository
        extends JpaRepository<IngredientsEntity, BigInteger>, JpaSpecificationExecutor<IngredientsEntity> {

    IngredientsEntity findAllByIngrName(String name);

    IngredientsEntity findFirstByIngrId(BigInteger id);
}



