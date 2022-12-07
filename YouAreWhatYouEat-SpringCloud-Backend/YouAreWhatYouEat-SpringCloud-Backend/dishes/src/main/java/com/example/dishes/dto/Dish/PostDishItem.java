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
public class PostDishItem {


    @JsonProperty("id")
    BigInteger id;
    @JsonProperty("dishName")
    String dishName;
    @JsonProperty("price")
    int price;
    @JsonProperty("description")
    String description;
    @JsonProperty("video")
    String video;
    @JsonProperty("picture")
    String picture;
    @JsonProperty("tags")
    List<String> tags;
    @JsonProperty("ings")
    List<String> ings;
}