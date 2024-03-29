package com.example.employee.repository;

import com.example.employee.entity.AwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AwardRepository
        extends JpaRepository<AwardEntity, String>, JpaSpecificationExecutor<AwardEntity>
{
    AwardEntity findFirstByLv(String lv);
}
