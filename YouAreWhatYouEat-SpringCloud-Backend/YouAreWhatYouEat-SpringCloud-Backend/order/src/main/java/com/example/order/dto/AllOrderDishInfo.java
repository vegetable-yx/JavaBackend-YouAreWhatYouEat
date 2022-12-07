package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllOrderDishInfo {
    private List<DishInfoDto> data=new ArrayList<DishInfoDto>();
    private AllDishSummary summary=new AllDishSummary();

    public void addDish(DishInfoDto dish)
    {
        data.add(dish);
    }
}
