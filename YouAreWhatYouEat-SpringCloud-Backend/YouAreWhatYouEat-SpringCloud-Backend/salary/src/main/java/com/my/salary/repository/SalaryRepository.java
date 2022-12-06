package com.my.salary.repository;

import com.my.salary.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepository
        extends
        JpaRepository<SalaryEntity, String>,
        JpaSpecificationExecutor<SalaryEntity> {
    Optional<SalaryEntity> findFirstByOccupation(String occupation);
    void deleteByOccupation(String occupation);
}

