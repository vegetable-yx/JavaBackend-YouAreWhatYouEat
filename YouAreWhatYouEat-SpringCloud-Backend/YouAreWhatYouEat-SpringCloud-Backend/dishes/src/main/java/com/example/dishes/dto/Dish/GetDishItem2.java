package com.example.dishes.dto.Dish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDishItem2 {

    BigInteger id;

    String dis_name;

    BigInteger price;

    String description;

    List<String>   tags;

}
