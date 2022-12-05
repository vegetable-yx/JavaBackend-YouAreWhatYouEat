package com.example.employee.repository;

import com.example.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends
        JpaRepository<EmployeeEntity, BigInteger>,
        JpaSpecificationExecutor<EmployeeEntity>
{
    List<EmployeeEntity> findAll();
    Optional<EmployeeEntity> findById(BigInteger id);
    void deleteAllById(BigInteger id);
    EmployeeEntity saveAndFlush(EmployeeEntity entity);
}
