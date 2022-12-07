package com.my.promotion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionRecord {
    @JsonProperty("promotion_id")
    private BigInteger promotionId;

    @JsonProperty("begin")
    private Timestamp begin;

    @JsonProperty("end")
    private Timestamp end;

    @JsonProperty("description")
    private String description;

    @JsonProperty("dishes")
    private List<PromotionDishRecord> dishes = new ArrayList<>();
}
