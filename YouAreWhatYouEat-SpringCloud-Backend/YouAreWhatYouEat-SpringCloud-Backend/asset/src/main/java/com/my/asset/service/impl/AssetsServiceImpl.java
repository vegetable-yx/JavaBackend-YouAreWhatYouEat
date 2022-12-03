package com.my.asset.service.impl;
import com.my.asset.entity.AssetsEntity;
import com.my.asset.repository.AssetRepository;
import com.my.asset.service.AssetsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AssetsServiceImpl implements AssetsService {
    @Resource
    private AssetRepository assetRepository;

    public AssetsEntity getAssets(String assetsType) {
        return assetRepository.findAssetsEntitiesByAssetsType(assetsType);
    }

}
