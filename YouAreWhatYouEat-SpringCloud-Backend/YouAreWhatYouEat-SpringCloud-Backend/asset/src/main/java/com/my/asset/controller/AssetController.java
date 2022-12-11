package com.my.asset.controller;

import com.my.asset.dto.Asset.AssetMessage;
import com.my.asset.dto.Asset.PostAddAssetInfo;
import com.my.asset.dto.Asset.PutUpdateAssetInfo;
import com.my.asset.dto.AssetRecord.AssetRecordMessage;
import com.my.asset.dto.AssetRecord.PostPutAssetRecordInfo;
import com.my.asset.dto.Repair.PostAddRepair;
import com.my.asset.service.AssetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/asset")
@CrossOrigin
public class AssetController {

    private final AssetsService assetsService;

    @Autowired
    public AssetController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @GetMapping("")
    public ResponseEntity<AssetMessage> getAsset(@RequestParam String assets_type) {
        AssetMessage msg = this.assetsService.getAsset(assets_type);
        if (msg == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(msg);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> postAddAsset(@RequestBody PostAddAssetInfo p) {
        return new ResponseEntity<>(assetsService.postAddAsset(p));
    }

    @PutMapping("")
    public ResponseEntity<Object> putUpdateAsset(@RequestBody PutUpdateAssetInfo p) {
        return new ResponseEntity<>(assetsService.putUpdateAsset(p));
    }

    @GetMapping("record")
    public ResponseEntity<AssetRecordMessage> getAssetRecord() {
        AssetRecordMessage msg = assetsService.getAssetRecord();
        if (msg == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(msg);
        }
    }

    @PostMapping("/record")
    public ResponseEntity<Object> postAddAssetRecord(@RequestBody PostPutAssetRecordInfo p) {
        return new ResponseEntity<>(assetsService.postAddAssetRecord(p));
    }

    @PutMapping("/record")
    public ResponseEntity<Object> putUpdateAssetRecord(@RequestBody PostPutAssetRecordInfo p) {
        return new ResponseEntity<>(assetsService.putUpdateAssetRecord(p));
    }

    @PostMapping("/repair")
    public ResponseEntity<Object> postAddRepair(@RequestBody PostAddRepair p) {
        return new ResponseEntity<>(assetsService.postAddAssetRepair(p));
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteAsset(@RequestParam String id) {
        return new ResponseEntity<>(assetsService.deleteAsset(id));
    }
}
