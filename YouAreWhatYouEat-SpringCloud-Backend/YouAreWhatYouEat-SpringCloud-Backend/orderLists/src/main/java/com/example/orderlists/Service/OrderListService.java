package com.example.orderlists.Service;

import com.example.orderlists.dto.GetDishNums;
import com.example.orderlists.dto.GetDishes;
import com.example.orderlists.dto.GetOrders;

public interface OrderListService {

    GetOrders getOders(String start, String end);

    GetDishes getDishes(String start, String end);

    GetDishNums getDishesNum(String start, String end);

}
