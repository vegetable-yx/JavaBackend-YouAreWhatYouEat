package com.my.asset.repository;

import com.my.asset.entity.AssetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssetRepository
        extends
        JpaRepository<AssetsEntity, String>,
        JpaSpecificationExecutor<AssetRepository> {
    AssetsEntity findAssetsEntitiesByAssetsType(String assetsType);

    AssetsEntity findAssetsEntitiesByAssetsId(String assetsId);
}