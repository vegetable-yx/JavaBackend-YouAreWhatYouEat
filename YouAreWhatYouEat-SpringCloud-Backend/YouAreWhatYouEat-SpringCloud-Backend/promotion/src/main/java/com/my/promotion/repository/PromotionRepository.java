package com.my.promotion.repository;

import com.my.promotion.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface PromotionRepository
        extends
        JpaRepository<PromotionEntity, BigInteger>,
        JpaSpecificationExecutor<PromotionEntity> {

}
