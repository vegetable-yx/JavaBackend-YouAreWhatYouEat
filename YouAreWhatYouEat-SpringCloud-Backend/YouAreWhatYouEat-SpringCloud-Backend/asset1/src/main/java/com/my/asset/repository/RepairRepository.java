package com.my.asset.repository;

import com.my.asset.entity.RepairEntity;
import com.my.asset.entity.RepairEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepairRepository
        extends
        JpaRepository<RepairEntity, RepairEntityPK>,
        JpaSpecificationExecutor<RepairEntity> {

}
