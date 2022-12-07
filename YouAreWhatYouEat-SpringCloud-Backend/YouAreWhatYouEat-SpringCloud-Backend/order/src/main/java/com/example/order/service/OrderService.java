package com.example.order.service;

import com.example.order.dto.AllOrderInfo;
import com.example.order.dto.ChangeOrderInfoRequest;
import com.example.order.dto.OrderByTableQuery;
import com.example.order.dto.OrderInfoDto;

public interface OrderService {
    OrderInfoDto getOrderByTable(OrderByTableQuery query);

    AllOrderInfo getAllOrder();

    boolean setOrderStatus(ChangeOrderInfoRequest request);
}
