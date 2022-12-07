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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Resource
    private PromotionRepository promotionRepository;

    @Resource
    private DishesRepository dishesRepository;

    @Resource
    private HasDishRepository hasDishRepository;

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
        System.out.println(p.getCover());
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
            promotionRepository.save(ret);
            return HttpStatus.CREATED;
        }
        catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

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
