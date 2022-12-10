package com.example.orderdish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishInfoSum {
    private List<DishInfo> dish_info=new ArrayList<DishInfo>();

    public void addDish(DishInfo dish)
    {
        dish_info.add(dish);
    }
}
