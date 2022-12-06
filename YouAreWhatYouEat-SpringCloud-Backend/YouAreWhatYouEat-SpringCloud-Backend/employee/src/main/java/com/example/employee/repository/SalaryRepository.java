package com.example.employee.repository;

import com.example.employee.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalaryRepository
extends JpaRepository<SalaryEntity, String>, JpaSpecificationExecutor<SalaryEntity>
{
    SalaryEntity findFirstByOccupation(String occ);
}
