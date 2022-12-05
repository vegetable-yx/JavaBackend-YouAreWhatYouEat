package com.my.asset.service;

import com.my.asset.dto.Asset.AssetMessage;
import com.my.asset.dto.Asset.PostAddAssetInfo;
import com.my.asset.dto.Asset.PutUpdateAssetInfo;
import com.my.asset.dto.AssetRecord.AssetRecordMessage;
import com.my.asset.dto.AssetRecord.PostPutAssetRecordInfo;
import com.my.asset.dto.Repair.PostAddRepair;
import org.springframework.http.HttpStatus;

public interface AssetsService {
    // Assets
    AssetMessage getAsset(String assetsType);

    HttpStatus postAddAsset(PostAddAssetInfo p);

    HttpStatus putUpdateAsset(PutUpdateAssetInfo p);

    // AssetsRecords
    AssetRecordMessage getAssetRecord();

    HttpStatus postAddAssetRecord(PostPutAssetRecordInfo p);

    HttpStatus putUpdateAssetRecord(PostPutAssetRecordInfo p);

    // Repairs
    HttpStatus postAddAssetRepair(PostAddRepair p);

    HttpStatus deleteAsset(String id);
}
