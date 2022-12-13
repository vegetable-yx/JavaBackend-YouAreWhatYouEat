package com.my.asset.repository;

import com.my.asset.entity.ManageEntity;
import com.my.asset.entity.ManageEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Date;
import java.util.Optional;

public interface ManagesRepository
        extends
        JpaRepository<ManageEntity, ManageEntityPK>,
        JpaSpecificationExecutor<ManageEntity> {
    Optional<ManageEntity> findFirstByAssetsIdAndManageCostAndManageDateAndManageTypeAndManageReason(
            String assetsId,
            String manageCost,
            Date manageDate,
            String manageType,
            String manageReason
    );
}
