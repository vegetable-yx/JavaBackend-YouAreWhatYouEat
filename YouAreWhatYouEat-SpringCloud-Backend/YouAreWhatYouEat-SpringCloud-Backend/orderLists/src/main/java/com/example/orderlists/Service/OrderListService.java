package com.example.orderlists.Service;

import com.example.orderlists.dto.GetOrders;

public interface OrderListService {

    GetOrders getOders(String start, String end);
}
