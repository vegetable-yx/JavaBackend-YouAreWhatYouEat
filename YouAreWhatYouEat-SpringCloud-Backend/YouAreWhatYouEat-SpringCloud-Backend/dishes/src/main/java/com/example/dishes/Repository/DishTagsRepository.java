package com.example.dishes.Repository;

import com.example.dishes.Entity.DishesEntity;
import com.example.dishes.Entity.DishtagsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public interface DishTagsRepository
        extends JpaRepository<DishtagsEntity, BigInteger>, JpaSpecificationExecutor<DishtagsEntity> {

    @Query("SELECT distinct  p.dtagName from DishtagsEntity p where p.dtagId=?1  ")
    Collection<String> FindDtagNameById(BigInteger id);


    @Query("SELECT distinct  p.dtagId from DishtagsEntity p where p.dtagName=?1  ")
    List<BigInteger> FindIdByName(String name);


}
