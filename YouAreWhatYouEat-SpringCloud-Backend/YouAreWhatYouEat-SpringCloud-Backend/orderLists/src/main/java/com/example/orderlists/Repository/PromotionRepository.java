package com.example.orderlists.Repository;

import com.example.orderlists.Entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface PromotionRepository
        extends JpaRepository<PromotionEntity, BigInteger>, JpaSpecificationExecutor<PromotionEntity> {

    List<PromotionEntity> findAll();

}
