package com.my.promotion.service;

import com.my.promotion.dto.PromotionPostRecord;
import com.my.promotion.dto.PromotionRecord;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.util.List;

public interface PromotionService {
    List<PromotionRecord> getPromotions();

    HttpStatus postPromotion(PromotionPostRecord p);

    HttpStatus deletePromotion(BigInteger id);
}
