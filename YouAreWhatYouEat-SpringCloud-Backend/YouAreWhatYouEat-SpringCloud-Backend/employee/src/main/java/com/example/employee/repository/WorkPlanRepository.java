package com.example.employee.repository;

import com.example.employee.entity.WorkPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface WorkPlanRepository
        extends JpaRepository<WorkPlanEntity, BigInteger>, JpaSpecificationExecutor<WorkPlanEntity>
    {
        WorkPlanEntity findFirstById(BigInteger id);
}
