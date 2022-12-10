package com.example.orderdish.repository;

import com.example.orderdish.entity.DinningtableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface TableRepository extends JpaRepository<DinningtableEntity, BigInteger>,JpaSpecificationExecutor<DinningtableEntity> {
    DinningtableEntity findByTableId(BigInteger tableId);
}

