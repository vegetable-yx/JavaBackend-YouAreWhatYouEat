package com.example.dishes.dto.Dish;
import com.example.dishes.Entity.DishHasTagEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDishItem {

    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;
    @JsonProperty("price")
    int price;
    @JsonProperty("description")
    String description;
    @JsonProperty("video")
    String video;
    @JsonProperty("picture")
    String picture;
    @JsonProperty("rate")
    String rate;
    @JsonProperty("tags")
    List<String> tags;
    @JsonProperty("ingredient")
    List<String> ingredient;


}
