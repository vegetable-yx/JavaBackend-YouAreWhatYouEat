package com.my.asset.service.impl;
import com.my.asset.dto.Asset.AssetInfo;
import com.my.asset.dto.Asset.AssetMessage;
import com.my.asset.dto.Asset.PostAddAssetInfo;
import com.my.asset.dto.Asset.PutUpdateAssetInfo;
import com.my.asset.dto.AssetRecord.AssetRecordInfo;
import com.my.asset.dto.AssetRecord.AssetRecordMessage;
import com.my.asset.dto.AssetRecord.PostPutAssetRecordInfo;
import com.my.asset.dto.Repair.PostAddRepair;
import com.my.asset.dto.Repair.RepairInfo;
import com.my.asset.entity.AssetsEntity;
import com.my.asset.entity.EmployeeEntity;
import com.my.asset.entity.ManageEntity;
import com.my.asset.entity.RepairEntity;
import com.my.asset.repository.AssetRepository;
import com.my.asset.repository.EmployeeRepository;
import com.my.asset.repository.ManagesRepository;
import com.my.asset.repository.RepairRepository;
import com.my.asset.service.AssetsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.util.*;

@Slf4j
@Service
public class AssetsServiceImpl implements AssetsService {
    @Resource
    private AssetRepository assetRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private ManagesRepository managesRepository;

    @Resource
    private RepairRepository repairRepository;

    @Override
    public AssetMessage getAsset(String assetsType) {
        AssetMessage msg = new AssetMessage();
        if (assetsType != null) {
            Optional<AssetsEntity> assetsEntity = assetRepository.findFirstByAssetsType(assetsType);
            if (assetsEntity.isEmpty()) { return null; }
            Optional<EmployeeEntity> assetsOwner = this.employeeRepository.findById(assetsEntity.get().getEmployeeId());
            if (assetsOwner.isEmpty()) { return null; }
            msg.setTotal(1);
            AssetInfo info = new AssetInfo();
            info.setAssetsId(assetsEntity.get().getAssetsId());
            info.setAssetsType(assetsEntity.get().getAssetsType());
            info.setEmployeeId(assetsEntity.get().getEmployeeId());
            info.setAssetsStatus(
                    assetsEntity.get().getAssetsStatus().equals(BigInteger.ZERO) ? "正常" : "已坏"
            );
            info.setEmployeeName(
                    assetsOwner.get().getName()
            );
            for (RepairEntity item : assetsEntity.get().getRepairEntities()) {
                RepairInfo repair = new RepairInfo();
                repair.setName(item.getName());
                repair.setPhone(item.getPhone());
                repair.setLongitude(item.getLongitude());
                repair.setLatitude(item.getLatitude());
                info.getRepair().add(repair);
            }
            msg.getData().add(info);
        }
        else {
            List<AssetsEntity> assetsEntities = this.assetRepository.findAll();
            for (AssetsEntity asset : assetsEntities) {
                Optional<EmployeeEntity> owner = this.employeeRepository.findById(asset.getEmployeeId());
                AssetInfo info = new AssetInfo();
                info.setAssetsId(asset.getAssetsId());
                info.setAssetsType(asset.getAssetsType());
                info.setEmployeeId(asset.getEmployeeId());
                info.setAssetsStatus(
                        asset.getAssetsStatus().equals(BigInteger.ZERO) ? "正常" : "已坏"
                );
                info.setEmployeeName(
                        owner.isPresent() ? owner.get().getName() : ""
                );
                for (RepairEntity item : asset.getRepairEntities()) {
                    RepairInfo repair = new RepairInfo();
                    repair.setName(item.getName());
                    repair.setPhone(item.getPhone());
                    repair.setLatitude(item.getLatitude());
                    repair.setLongitude(item.getLongitude());
                    info.getRepair().add(repair);
                }
                msg.getData().add(info);
            }
            msg.setTotal(msg.getData().size());
        }
        return msg;
    }

    @Transactional
    @Override
    public HttpStatus postAddAsset(PostAddAssetInfo p) {
        if (p.getEmployeeId() == null || p.getAssetsStatus() == null || p.getAssetsType() == null) {
            return HttpStatus.BAD_REQUEST;
        }
        AssetsEntity info = new AssetsEntity();
        info.setEmployeeId(p.getEmployeeId());
        info.setAssetsType(p.getAssetsType());
        info.setAssetsStatus(p.getAssetsStatus().equals("正常") ? BigInteger.ZERO : BigInteger.valueOf(-1));

        log.info("info: " + info.toString());
        String str = "";
        Random rand = new Random();
        int r = rand.nextInt();
        if (r % 3 == 0) { str += "A"; }
        else if (r % 3 == 1) { str += "B"; }
        else { str += "C"; }

        List<AssetsEntity> assets = assetRepository.findAll();
        int num = 0;
        for (AssetsEntity asset : assets) {
            if (asset.getAssetsId().charAt(0) == str.charAt(0)) {
                if (Integer.parseInt(asset.getAssetsId().substring(1)) > num) {
                    num = Integer.parseInt(asset.getAssetsId().substring(1));
                }
            }
        }
        info.setAssetsId(str + (num + 1));

        try {
            log.info(info.toString());
            assetRepository.save(info);
            return HttpStatus.CREATED;
        }
        catch (Exception ex) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Transactional
    @Override
    public HttpStatus putUpdateAsset(PutUpdateAssetInfo p) {
        if (p.getEmployeeId() == null || p.getAssetsStatus() == null || p.getAssetsType() == null || p.getAssetsId() == null) {
            return HttpStatus.BAD_REQUEST;
        }
        Optional<AssetsEntity> info = assetRepository.findFirstByAssetsId(p.getAssetsId());
        if (info.isEmpty()) {
            return HttpStatus.NO_CONTENT;
        }
        try {
            info.get().setEmployeeId(p.getEmployeeId());
            info.get().setAssetsType(p.getAssetsType());
            info.get().setAssetsStatus(p.getAssetsStatus() == "正常" ? BigInteger.ZERO : BigInteger.valueOf(-1));
            info.get().setAssetsId(p.getAssetsId());
            assetRepository.saveAndFlush(info.get());
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    private boolean checkPostPutAssetRecordInfo(PostPutAssetRecordInfo p) {
        return p.getAssetsId() == null
                || p.getEmployeeId() == null
                || p.getManageCost() == null
                || p.getManageType() == null
                || p.getManageDate() == null;
    }

    @Override
    public AssetRecordMessage getAssetRecord() {
        AssetRecordMessage msg = new AssetRecordMessage();
//        List<ManageEntity> records = managesRepository.findAll();
//        for (ManageEntity record : records) {
//            Optional<EmployeeEntity> employee = employeeRepository.findById(record.getEmployeeId());
//            Optional<AssetsEntity> assets = assetRepository.findById(record.getAssetsId());
//            AssetRecordInfo info = new AssetRecordInfo();
//            info.setAssetsId(assets.map(AssetsEntity::getAssetsId).orElse(null));
//            info.setAssetsType(assets.map(AssetsEntity::getAssetsType).orElse(null));
//            info.setEmployeeId(employee.map(EmployeeEntity::getId).orElse(null));
//            info.setEmployeeName(employee.map(EmployeeEntity::getName).orElse(null));
//            info.setManageType(record.getManageType());
//            info.setManageReason(record.getManageReason());
//            info.setManageDate(record.getManageDate().toString());
//            info.setManageCost(record.getManageCost());
//            msg.getData().add(info);
//        }
//        msg.setTotal(msg.getData().size());
        return msg;
    }

    @Transactional
    @Override
    public HttpStatus postAddAssetRecord(PostPutAssetRecordInfo p) {
        if (checkPostPutAssetRecordInfo(p)) {
            return HttpStatus.BAD_REQUEST;
        }
        Date manageDate = null;
        try {
            manageDate = Date.valueOf(p.getManageDate());
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        ManageEntity info = new ManageEntity();
        info.setAssetsId(p.getAssetsId());
        info.setEmployeeId(p.getEmployeeId());
        info.setManageDate(manageDate);
        info.setManageType(p.getManageType());
        info.setManageCost(p.getManageCost());
        info.setManageReason(p.getManageReason());
        log.info("Info: " + info.toString());
        try {
            managesRepository.save(info);
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Transactional
    @Override
    public HttpStatus putUpdateAssetRecord(PostPutAssetRecordInfo p) {
        if (checkPostPutAssetRecordInfo(p)) {
            return HttpStatus.BAD_REQUEST;
        }
        Date manageDate = null;
        try {
            manageDate = Date.valueOf(p.getManageDate());
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        Optional<ManageEntity> info = managesRepository.findFirstByAssetsIdAndManageCostAndManageDateAndManageTypeAndManageReason(
                p.getAssetsId(),
                p.getManageCost(),
                manageDate,
                p.getManageType(),
                p.getManageReason()
        );
        if (info.isEmpty()) {
            return HttpStatus.NO_CONTENT;
        }
        info.get().setEmployeeId(p.getEmployeeId());

        try {
            managesRepository.saveAndFlush(info.get());
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Transactional
    @Override
    public HttpStatus postAddAssetRepair(PostAddRepair p) {
        RepairEntity repair = new RepairEntity();
        repair.setAssetsid(p.getAssetsId());
        repair.setName(p.getName());
        repair.setPhone(p.getPhone());
        repair.setLongitude(p.getLongitude());
        repair.setLatitude(p.getLatitude());

        try {
            repairRepository.save(repair);
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Transactional
    @Override
    public HttpStatus deleteAsset(String id) {
        if (id == null) {
            return HttpStatus.BAD_REQUEST;
        }
        Optional<AssetsEntity> info = assetRepository.findFirstByAssetsId(id);
        if (info.isEmpty()) {
            return HttpStatus.NO_CONTENT;
        }

        try {
            assetRepository.deleteById(id);
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
