package com.example.employee.repository;

import com.example.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface EmployeeRepository extends
        JpaRepository<EmployeeEntity, BigInteger>,
        JpaSpecificationExecutor<EmployeeEntity>
{
    EmployeeEntity findFirstById(int id);
}
