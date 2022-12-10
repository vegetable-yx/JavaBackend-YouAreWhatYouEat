package com.example.orderdish.repository;

import com.example.orderdish.entity.CommentOnDishEntity;
import com.example.orderdish.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface PromotionRepository extends JpaRepository<PromotionEntity, BigInteger>, JpaSpecificationExecutor<PromotionEntity> {

    PromotionEntity findByPromotionId(BigInteger promotionId);
}
