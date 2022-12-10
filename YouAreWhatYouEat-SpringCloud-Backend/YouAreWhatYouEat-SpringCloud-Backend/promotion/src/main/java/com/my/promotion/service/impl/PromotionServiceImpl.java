package com.my.promotion.service.impl;

import com.my.promotion.dto.*;
import com.my.promotion.entity.DishesEntity;
import com.my.promotion.entity.HasDishEntity;
import com.my.promotion.entity.PromotionEntity;
import com.my.promotion.repository.DishesRepository;
import com.my.promotion.repository.HasDishRepository;
import com.my.promotion.repository.PromotionRepository;
import com.my.promotion.service.PromotionService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Resource
    private PromotionRepository promotionRepository;

    @Resource
    private DishesRepository dishesRepository;

    @Resource
    private HasDishRepository hasDishRepository;

    @Value("${myConfiguration.path}")
    private String imagePath;

    @Override
    public List<PromotionRecord> getPromotions() {
        List<PromotionRecord> ret = new ArrayList<>();
        List<PromotionEntity> promotions = promotionRepository.findAll();
        for (PromotionEntity p : promotions) {
            PromotionRecord pr = new PromotionRecord();
            pr.setPromotionId(p.getPromotionId());
            pr.setDescription(p.getDescription());
            pr.setBegin(p.getStartTime());
            pr.setEnd(p.getEndTime());
            Collection<HasDishEntity> d = p.getCorrespondingDishTags();
            for (HasDishEntity di : d) {
                PromotionDishRecord dt = new PromotionDishRecord();
                if (di.getDiscount() != null) {
                    dt.setDiscount(di.getDiscount());
                }
                else {
                    dt.setDiscount(BigDecimal.ZERO);
                }
                dt.setDish(new PromotionDish(di.getDishes()));
                pr.getDishes().add(dt);
            }
            ret.add(pr);
        }
        return ret;
    }

    @Transactional
    @Override
    public HttpStatus postPromotion(PromotionPostRecord p) {
//        System.out.println(p.getCover());
        PromotionEntity ret = new PromotionEntity();
        List<PromotionEntity> pros = promotionRepository.findAll();
        Optional<PromotionEntity> maxId = pros.stream().max(
                (o1, o2) -> o1.getPromotionId().compareTo(o2.getPromotionId())
        );
        BigInteger pId = BigInteger.ONE;
        if (maxId.isPresent()) {
            pId = maxId.get().getPromotionId().add(BigInteger.ONE);
        }

        ret.setPromotionId(pId);
        Timestamp startTime, endTime;
        try {
            startTime = Timestamp.valueOf(p.getStart());
            endTime   = Timestamp.valueOf(p.getEnd());
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        ret.setStartTime(startTime);
        ret.setEndTime(endTime);
        ret.setDescription(p.getDescription());
        ret.setCorrespondingDishTags(new ArrayList<>());
        for (PromotionPostDishRecord di : p.getDishes()) {
            Optional<DishesEntity> dish = dishesRepository.findFirstByDishName(di.getName());
            if (dish.isEmpty()) {
                continue;
            }
            else {
                HasDishEntity newRelation = new HasDishEntity();
                newRelation.setDiscount(di.getDiscount());
                newRelation.setDishId(dish.get().getDishId());
                newRelation.setPromotionId(ret.getPromotionId());
                newRelation.setPromotion(ret);
                newRelation.setDishes(dish.get());
                ret.getCorrespondingDishTags().add(newRelation);
            }
        }
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            if (p.getCover() != null && !p.getCover().isEmpty()) {
                byte[] b = decoder.decode(p.getCover());
                String imgFilePath = imagePath + "promotions/promotion_" + pId.toString() + ".png";
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
            }
            promotionRepository.save(ret);
            return HttpStatus.CREATED;
        }
        catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Transactional
    @Override
    public HttpStatus deletePromotion(BigInteger id) {
        if (!promotionRepository.existsById(id)) {
            return HttpStatus.NO_CONTENT;
        }
        try {
            promotionRepository.deleteById(id);
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
