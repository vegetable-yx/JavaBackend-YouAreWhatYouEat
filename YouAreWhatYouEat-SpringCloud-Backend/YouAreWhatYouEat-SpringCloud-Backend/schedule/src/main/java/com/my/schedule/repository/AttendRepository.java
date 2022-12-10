package com.my.schedule.repository;

import com.my.schedule.entity.AttendEntity;
import com.my.schedule.entity.AttendEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttendRepository
        extends
        JpaRepository<AttendEntity, AttendEntityPK>,
        JpaSpecificationExecutor<AttendEntity> {
}
