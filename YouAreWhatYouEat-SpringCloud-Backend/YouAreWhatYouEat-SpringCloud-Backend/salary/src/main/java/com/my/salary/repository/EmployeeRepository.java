package com.my.salary.repository;

import com.my.salary.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface EmployeeRepository
        extends
        JpaRepository<EmployeeEntity, BigInteger>,
        JpaSpecificationExecutor<EmployeeEntity> {
}
