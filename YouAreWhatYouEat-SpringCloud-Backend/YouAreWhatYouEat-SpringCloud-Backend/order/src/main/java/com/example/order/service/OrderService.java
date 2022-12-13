package com.example.order.service;

import com.example.order.dto.*;

import java.math.BigInteger;

public interface OrderService {
    OrderInfoDto getOrderByTable(BigInteger query);

    OrderInfoDto getOrderById(String query);

    AllOrderInfo getAllOrder();

    boolean setOrderStatus(ChangeOrderInfoRequest request);

    AllOrderDishInfo getAllDishInOrder(String query);
}
