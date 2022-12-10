package com.example.orderdish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionInfo {
    @JsonProperty("promotion_id")
    private BigInteger promotion_id;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("description")
    private String description;

    @JsonProperty("dishes")
    private List<PromotionDishInfo> dishes=new ArrayList<PromotionDishInfo>();

    public void addPromotionDishInfo(PromotionDishInfo dish)
    {
        dishes.add(dish);
    }
}
