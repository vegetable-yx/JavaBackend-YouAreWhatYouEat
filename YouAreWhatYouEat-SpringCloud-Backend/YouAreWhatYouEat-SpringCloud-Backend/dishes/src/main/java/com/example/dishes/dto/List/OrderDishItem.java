package com.example.dishes.dto.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishItem {

    String dishName;
    String dishStatus;
    String dishOrderId;
    String remark;
}
