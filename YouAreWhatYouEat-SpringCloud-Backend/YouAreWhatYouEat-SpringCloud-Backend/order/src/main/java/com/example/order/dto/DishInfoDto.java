package com.example.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishInfoDto {
    @JsonProperty("dish_order_id")
    private String dish_order_id;

    @JsonProperty("order_id")
    private String order_id;

    @JsonProperty("dish_id")
    private BigInteger dish_id;

    @JsonProperty("final_payment")
    private Double final_payment;

    @JsonProperty("original_price")
    private Double original_price;

    @JsonProperty("dish_status")
    private String dish_status;
}
