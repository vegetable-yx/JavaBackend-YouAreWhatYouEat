package com.my.sensor.repository;

import com.my.sensor.entity.SensorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface SensorsRepository
        extends
        JpaRepository<SensorsEntity, BigInteger>,
        JpaSpecificationExecutor<SensorsEntity> {

}
