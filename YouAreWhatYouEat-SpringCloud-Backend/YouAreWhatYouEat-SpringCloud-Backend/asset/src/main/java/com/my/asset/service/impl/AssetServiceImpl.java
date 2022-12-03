package com.my.asset.service.impl;

import com.my.asset.dto.AssetMessage;
import com.my.asset.mapper.AssetMapper;
import com.my.asset.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetServiceImpl implements AssetService {
    private AssetMapper assetMapper;

    @Autowired
    public AssetServiceImpl(AssetMapper assetMapper) {
        this.assetMapper = assetMapper;
    }

    @Override
    public AssetMessage getAsset(String assets_type) {
        return null;
    }
}
