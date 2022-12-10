package com.example.orderdish.dto.submitorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSubmit {
    private List<DishesInfo> dishes_info;
    private String username;
    private BigInteger table_id;
}
