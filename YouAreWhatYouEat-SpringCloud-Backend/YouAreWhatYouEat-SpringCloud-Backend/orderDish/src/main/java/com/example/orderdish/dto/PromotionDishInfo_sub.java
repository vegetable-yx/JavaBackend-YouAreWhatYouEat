package com.example.orderdish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDishInfo_sub {
    @JsonProperty("dish_id")
    private BigInteger dish_id;

    @JsonProperty("dish_name")
    private String dish_name;

    @JsonProperty("dish_price")
    private Double dish_price;

    @JsonProperty("dish_description")
    private String dish_description;

    @JsonProperty("dish_picture")
    private String dish_picture;
}
