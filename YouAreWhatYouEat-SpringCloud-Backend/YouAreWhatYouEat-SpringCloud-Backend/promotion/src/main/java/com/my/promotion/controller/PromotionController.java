package com.my.promotion.controller;

import com.my.promotion.dto.PromotionPostRecord;
import com.my.promotion.dto.PromotionRecord;
import com.my.promotion.entity.HasDishEntity;
import com.my.promotion.repository.HasDishRepository;
import com.my.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/promotions")
@CrossOrigin
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("")
    public ResponseEntity<List<PromotionRecord>> getPromotions() {
        List<PromotionRecord> ret = promotionService.getPromotions();
        if (ret == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(ret);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> postPromotion(@RequestBody PromotionPostRecord p) {
        System.out.println(p.toString());
        return new ResponseEntity<>(promotionService.postPromotion(p));
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deletePromotion(@RequestParam BigInteger id) {
        return new ResponseEntity<>(promotionService.deletePromotion(id));
    }
}
