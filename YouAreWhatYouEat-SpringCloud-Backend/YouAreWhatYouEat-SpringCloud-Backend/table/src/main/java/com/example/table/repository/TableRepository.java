package com.example.table.repository;

import com.example.table.entity.DinningtableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface TableRepository extends JpaRepository<DinningtableEntity, BigInteger>,JpaSpecificationExecutor<DinningtableEntity> {
    DinningtableEntity findByTableId(BigInteger tableId);
}

