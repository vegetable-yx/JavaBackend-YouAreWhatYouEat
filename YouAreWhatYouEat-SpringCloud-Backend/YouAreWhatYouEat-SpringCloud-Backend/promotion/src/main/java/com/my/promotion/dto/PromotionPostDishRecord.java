package com.my.promotion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionPostDishRecord {
    @JsonProperty("name")
    private String name;

    @JsonProperty("discount")
    private BigDecimal discount;
}
