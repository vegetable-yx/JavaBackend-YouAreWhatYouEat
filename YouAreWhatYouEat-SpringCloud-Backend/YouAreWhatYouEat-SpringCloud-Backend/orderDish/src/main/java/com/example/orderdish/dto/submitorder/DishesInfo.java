package com.example.orderdish.dto.submitorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishesInfo {
    private BigInteger dish_id;
    private BigInteger dish_num;
    private Double dish_price_to_pay;
    private String remark;
}
