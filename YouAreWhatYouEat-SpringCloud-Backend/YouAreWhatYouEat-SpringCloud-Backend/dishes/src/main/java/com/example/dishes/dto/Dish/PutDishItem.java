package com.example.dishes.dto.Dish;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutDishItem {


    @JsonProperty("id")
    BigInteger id;
    @JsonProperty("dish_name")
    String dishName;
    @JsonProperty("price")
    int price;
    @JsonProperty("description")
    String description;
    @JsonProperty("tags")
    List<String> tags;
    @JsonProperty("ingredient")
    List<String> ings;
}
