package com.example.ingredientrecords.Repository;

import com.example.ingredientrecords.Entity.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public interface IngredientRepository
        extends JpaRepository<IngredientsEntity, BigInteger>, JpaSpecificationExecutor<IngredientsEntity> {
    @Query("select distinct p.ingrName from IngredientsEntity p where p.ingrId=?1")
    List<String> findNameById(BigInteger Id);

    @Query("select distinct p.ingrId from IngredientsEntity p where p.ingrName=?1")
    List<BigInteger> findIdByName(String name);
}
