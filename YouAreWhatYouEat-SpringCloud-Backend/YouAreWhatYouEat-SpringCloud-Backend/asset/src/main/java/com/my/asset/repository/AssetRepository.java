package com.my.asset.repository;

import com.my.asset.entity.AssetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AssetRepository
        extends
        JpaRepository<AssetsEntity, String>,
        JpaSpecificationExecutor<AssetRepository> {

    Optional<AssetsEntity> findFirstByAssetsType(String assetsType);

    Optional<AssetsEntity> findFirstByAssetsId(String assetsId);

}