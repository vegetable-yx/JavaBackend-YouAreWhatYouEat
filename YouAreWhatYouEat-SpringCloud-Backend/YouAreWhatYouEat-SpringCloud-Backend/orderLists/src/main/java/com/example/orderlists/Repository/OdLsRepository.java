package com.example.orderlists.Repository;

import com.example.orderlists.Entity.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface OdLsRepository
        extends JpaRepository<OrderlistEntity, BigInteger>, JpaSpecificationExecutor<OrderlistEntity>
{

    OrderlistEntity findFirstByOrderId(String id);
}
