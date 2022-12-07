package com.example.order.controller;

import com.example.order.dto.OrderByTableQuery;
import com.example.order.dto.OrderInfoDto;
import com.example.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/v1")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService=orderService;
    }

    @RequestMapping("/orderByTable")
    @ResponseBody
    public OrderInfoDto getOrderByTableId(OrderByTableQuery query)
    {
        return orderService.getOrderByTable(query);
    }
}
