package com.example.orderdish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishAllInfo {
    private BigInteger dish_id;
    private String dish_name;
    private String dish_picture;
    private String dish_video;
    private Double dish_price;
    private Double dish_rate;
    private BigInteger dish_sell_num;
    private String dish_description;
    private List<Double> dish_discount=new ArrayList<Double>();
    private List<String> dish_tag=new ArrayList<String>();
    private List<DishComment> dish_comment=new ArrayList<DishComment>();

    public void addComment(DishComment comment)
    {
        dish_comment.add(comment);
    }
}
