package com.my.promotion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDishRecord {
    @JsonProperty("dish")
    private PromotionDish dish;

    @JsonProperty("discount")
    private BigDecimal discount;
}
