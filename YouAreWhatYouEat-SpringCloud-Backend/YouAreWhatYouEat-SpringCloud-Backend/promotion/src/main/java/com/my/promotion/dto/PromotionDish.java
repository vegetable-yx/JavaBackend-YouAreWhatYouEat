package com.my.promotion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.my.promotion.entity.DishesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDish {
    @JsonProperty("dish_id")
    private BigInteger dishId;

    @JsonProperty("dish_name")
    private String dishName;

    @JsonProperty("dish_price")
    private BigDecimal dishPrice;

    @JsonProperty("dish_description")
    private String dishDescription;

    public PromotionDish(DishesEntity d) {
        this.dishId = d.getDishId();
        this.dishName = d.getDishName();
        this.dishDescription = d.getDishDescription();
        this.dishPrice = d.getDishPrice();
    }
}
