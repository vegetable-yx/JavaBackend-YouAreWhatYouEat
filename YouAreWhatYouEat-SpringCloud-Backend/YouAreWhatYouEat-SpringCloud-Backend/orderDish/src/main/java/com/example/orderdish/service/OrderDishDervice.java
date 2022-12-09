package com.example.orderdish.service;

import com.example.orderdish.dto.DishInfo;

import java.util.List;

public interface OrderDishDervice {
    List<DishInfo> getDishInfoInOrder(String orderId);
}
