package com.my.sensor.repository;

import com.my.sensor.entity.SensorLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public interface SensorLogsRepository
        extends
        JpaRepository<SensorLogEntity, BigInteger>,
        JpaSpecificationExecutor<SensorLogEntity> {
    List<SensorLogEntity> findBySlogTimeBetween(Timestamp begin, Timestamp end);
}
