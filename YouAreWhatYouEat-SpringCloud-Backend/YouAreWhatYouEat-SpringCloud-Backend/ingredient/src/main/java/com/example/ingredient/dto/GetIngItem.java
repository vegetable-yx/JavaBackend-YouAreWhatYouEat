package com.example.ingredient.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIngItem {

    BigInteger ingId;

    String ingName;

    String ingType;

    String ingDescription;

}
