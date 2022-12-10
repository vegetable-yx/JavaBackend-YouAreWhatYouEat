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

    @JsonProperty("dish_id")
    BigInteger id;
    @JsonProperty("dish_name")
    String dishName;
    @JsonProperty("dish_price")
    int price;
    @JsonProperty("dish_description")
    String description;
    @JsonProperty("dish_video")
    String video;
    @JsonProperty("dish_picture")
    String picture;
    @JsonProperty("dish_rate")
    String rate;
    @JsonProperty("dish_tags")
    List<String> tags;
    @JsonProperty("dish_ings")
    List<String> ings;


}
