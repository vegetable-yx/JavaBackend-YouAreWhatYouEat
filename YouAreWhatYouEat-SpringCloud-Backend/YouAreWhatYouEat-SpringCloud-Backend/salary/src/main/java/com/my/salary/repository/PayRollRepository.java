package com.my.salary.repository;

import com.my.salary.entity.PayrollEntity;
import com.my.salary.entity.PayrollEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PayRollRepository
        extends
        JpaRepository<PayrollEntity, PayrollEntityPK>,
        JpaSpecificationExecutor<PayrollEntity> {
}
