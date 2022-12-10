package com.example.orderlists.Repository;

import com.example.orderlists.Entity.DishesEntity;
import com.example.orderlists.Entity.DishtagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface DishTagsRepository
        extends JpaRepository<DishtagsEntity, BigInteger>, JpaSpecificationExecutor<DishtagsEntity> {

    DishtagsEntity findFirstByDtagId(BigInteger id);
}
