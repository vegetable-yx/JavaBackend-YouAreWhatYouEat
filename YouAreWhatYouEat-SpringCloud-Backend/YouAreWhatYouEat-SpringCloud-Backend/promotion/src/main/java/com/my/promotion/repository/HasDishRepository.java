package com.my.promotion.repository;

import com.my.promotion.entity.HasDishEntity;
import com.my.promotion.entity.HasDishEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.Optional;

public interface HasDishRepository
        extends
        JpaRepository<HasDishEntity, HasDishEntityPK>,
        JpaSpecificationExecutor<HasDishEntity> {
    Optional<HasDishEntity> findFirstByDishIdAndPromotionId(BigInteger dishId, BigInteger promotionId);
}
