package com.my.schedule.repository;

import com.my.schedule.entity.WorkPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface WorkPlanRepository
        extends
        JpaRepository<WorkPlanEntity, BigInteger>,
        JpaSpecificationExecutor<WorkPlanEntity> {
    List<WorkPlanEntity> findAllByTimeStartAfterAndTimeEndBefore(
            Timestamp start,
            Timestamp end
    );

}
