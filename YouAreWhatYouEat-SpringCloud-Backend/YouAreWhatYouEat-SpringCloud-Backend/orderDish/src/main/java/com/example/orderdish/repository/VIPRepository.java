package com.example.orderdish.repository;

import com.example.orderdish.entity.DinningtableEntity;
import com.example.orderdish.entity.VipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface VIPRepository extends JpaRepository<VipEntity, String>,JpaSpecificationExecutor<VipEntity> {
    VipEntity findByUserName(String userName);
}

