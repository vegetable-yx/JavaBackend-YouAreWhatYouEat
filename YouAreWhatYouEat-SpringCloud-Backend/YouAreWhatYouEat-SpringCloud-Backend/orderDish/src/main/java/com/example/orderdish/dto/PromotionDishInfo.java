package com.example.orderdish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDishInfo {
    @JsonProperty("dish")
    private PromotionDishInfo_sub dish;

    @JsonProperty("discount")
    private Double discount;
}
