package com.example.vip.Repository;

import com.example.vip.Entity.VipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface VipRepository
        extends JpaRepository<VipEntity, BigInteger>, JpaSpecificationExecutor<VipEntity> {

    VipEntity findFirstByUserName(String name);


}
