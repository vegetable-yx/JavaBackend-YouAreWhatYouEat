package com.my.asset.service;

import com.my.asset.entity.AssetsEntity;

import java.util.Collection;

public interface AssetsService {

    public AssetsEntity getAssets(String assetsType);

    public Collection<AssetsEntity> getAssetsByEmployeeName(String name);

}
