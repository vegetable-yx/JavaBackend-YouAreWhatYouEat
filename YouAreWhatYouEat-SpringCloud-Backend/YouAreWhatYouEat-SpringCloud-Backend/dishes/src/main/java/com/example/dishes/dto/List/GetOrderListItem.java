package com.example.dishes.dto.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderListItem {

    String order_id;

    String order_status;

    List<OrderDishItem> dish ;
}
