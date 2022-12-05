package com.example.employee.repository;

import com.example.employee.entity.AttendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public interface AttendRepository
        extends JpaRepository<AttendEntity, BigInteger>, JpaSpecificationExecutor<AttendEntity> {
    public List<AttendEntity> findAllByEmployeeId(BigInteger id);
}
