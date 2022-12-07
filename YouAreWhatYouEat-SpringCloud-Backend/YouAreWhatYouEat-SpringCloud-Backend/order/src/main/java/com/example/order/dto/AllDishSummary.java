package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AllDishSummary {
    private Double total_price;

    public AllDishSummary()
    {
        total_price=0d;
    }
}
