package com.example.orderlists.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishNum {
    String name;
    BigInteger id;
    List<String> tags;
    BigInteger price;
    int times;
    BigInteger totalPay;

}
