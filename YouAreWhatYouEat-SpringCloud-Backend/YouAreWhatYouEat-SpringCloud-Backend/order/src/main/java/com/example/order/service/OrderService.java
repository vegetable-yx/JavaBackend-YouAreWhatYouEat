package com.example.order.service;

import com.example.order.dto.*;

public interface OrderService {
    OrderInfoDto getOrderByTable(OrderByTableQuery query);

    AllOrderInfo getAllOrder();

    boolean setOrderStatus(ChangeOrderInfoRequest request);

    AllOrderDishInfo getAllDishInOrder(OrderByIdQuery query);
}
