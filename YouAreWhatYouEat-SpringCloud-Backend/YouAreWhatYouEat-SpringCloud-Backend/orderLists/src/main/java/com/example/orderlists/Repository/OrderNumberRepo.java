package com.example.orderlists.Repository;

import com.example.orderlists.Entity.OrderNumberEntity;
import com.example.orderlists.Entity.OrderNumberEntityPK;
import com.example.orderlists.Entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface OrderNumberRepo     extends JpaRepository<OrderNumberEntity, BigInteger>, JpaSpecificationExecutor<OrderNumberEntity> {

    List<OrderNumberEntity> findAllByUserName(String name);
}
