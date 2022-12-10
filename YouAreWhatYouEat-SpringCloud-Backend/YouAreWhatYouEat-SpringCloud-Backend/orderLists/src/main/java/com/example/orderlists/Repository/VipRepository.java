package com.example.orderlists.Repository;

import com.example.orderlists.Entity.PromotionEntity;
import com.example.orderlists.Entity.VipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface VipRepository
        extends JpaRepository<VipEntity, BigInteger>, JpaSpecificationExecutor<VipEntity> {
}
