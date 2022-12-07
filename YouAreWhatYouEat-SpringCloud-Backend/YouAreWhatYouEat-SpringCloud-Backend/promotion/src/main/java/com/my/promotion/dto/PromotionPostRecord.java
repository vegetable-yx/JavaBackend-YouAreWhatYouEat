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
public class PromotionPostRecord {
    @JsonProperty("start")
    private String start;

    @JsonProperty("end")
    private String end;

    @JsonProperty("description")
    private String description;

    @JsonProperty("cover")
    private String cover;

    @JsonProperty("dishes")
    private List<PromotionPostDishRecord> dishes = new ArrayList<>();
}
