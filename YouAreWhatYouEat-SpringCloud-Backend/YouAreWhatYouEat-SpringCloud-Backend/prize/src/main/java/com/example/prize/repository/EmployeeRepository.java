package com.example.prize.repository;

import com.example.prize.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface EmployeeRepository
    extends JpaRepository<EmployeeEntity, BigInteger>, JpaSpecificationExecutor<EmployeeEntity>
{
    public EmployeeEntity findFirstById(BigInteger id);
}
