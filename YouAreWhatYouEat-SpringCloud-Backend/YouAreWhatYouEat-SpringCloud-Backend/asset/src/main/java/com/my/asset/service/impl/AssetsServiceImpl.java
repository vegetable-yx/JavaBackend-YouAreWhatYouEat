package com.my.asset.service.impl;
import com.my.asset.entity.AssetsEntity;
import com.my.asset.entity.EmployeeEntity;
import com.my.asset.repository.AssetRepository;
import com.my.asset.repository.EmployeeRepository;
import com.my.asset.service.AssetsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AssetsServiceImpl implements AssetsService {
    @Resource
    private AssetRepository assetRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    public AssetsEntity getAssets(String assetsType) {
        return assetRepository.findAssetsEntitiesByAssetsType(assetsType);
    }

    @Override
    public Collection<AssetsEntity> getAssetsByEmployeeName(String name) {
        EmployeeEntity employeeEntity = employeeRepository.findByName(name);
        return employeeEntity.assetsById;
    }
}
