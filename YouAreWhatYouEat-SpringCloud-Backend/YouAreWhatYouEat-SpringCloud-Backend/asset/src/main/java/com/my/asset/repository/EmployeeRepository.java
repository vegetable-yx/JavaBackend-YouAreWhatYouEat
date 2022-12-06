package com.my.asset.repository;

import com.my.asset.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface EmployeeRepository
        extends
        JpaRepository<EmployeeEntity, BigInteger>,
        JpaSpecificationExecutor<EmployeeEntity> {
}
