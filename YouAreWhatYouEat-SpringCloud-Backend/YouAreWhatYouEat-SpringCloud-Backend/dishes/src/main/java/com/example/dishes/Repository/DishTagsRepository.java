package com.example.dishes.Repository;

import com.example.dishes.Entity.DishesEntity;
import com.example.dishes.Entity.DishtagsEntity;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Collection;

public interface DishTagsRepository
        extends JpaRepository<DishtagsEntity, BigInteger>, JpaSpecificationExecutor<DishtagsEntity> {

    @Query("SELECT distinct  p.dtagName from DishtagsEntity p where p.dtagId=?1  ")
    Collection<String> FindDtagNameById(BigInteger id);
}
