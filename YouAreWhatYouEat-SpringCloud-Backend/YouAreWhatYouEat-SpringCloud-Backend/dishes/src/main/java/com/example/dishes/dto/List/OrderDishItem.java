package com.example.dishes.dto.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishItem {

    String dish_name;
    String status;
    String dish_order_id;
    String remark;
}
