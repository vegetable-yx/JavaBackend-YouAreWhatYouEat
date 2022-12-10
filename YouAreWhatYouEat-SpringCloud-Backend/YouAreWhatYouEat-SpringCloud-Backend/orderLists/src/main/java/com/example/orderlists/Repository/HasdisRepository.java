package com.example.orderlists.Repository;

import com.example.orderlists.Entity.HasdishEntity;
import com.example.orderlists.Entity.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface HasdisRepository
        extends JpaRepository<HasdishEntity, BigInteger>, JpaSpecificationExecutor<HasdishEntity> {

    List<HasdishEntity> findAllByPromotionId(BigInteger promotionId);
}
