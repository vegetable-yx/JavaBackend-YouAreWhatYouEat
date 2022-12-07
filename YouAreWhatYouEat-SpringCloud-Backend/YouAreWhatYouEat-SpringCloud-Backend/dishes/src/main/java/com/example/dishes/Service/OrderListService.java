package com.example.dishes.Service;

import com.example.dishes.dto.Dish.PutDishItem;
import com.example.dishes.dto.List.GetOrderListItem;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface OrderListService {

    public List<GetOrderListItem> getOrderList();

    HttpStatus putUpdateDishStatus(PutDishItem item);
}
