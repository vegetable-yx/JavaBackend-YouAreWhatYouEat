package com.example.orderdish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishRealPrice {
    private Double price;
    private Double discount;
}
