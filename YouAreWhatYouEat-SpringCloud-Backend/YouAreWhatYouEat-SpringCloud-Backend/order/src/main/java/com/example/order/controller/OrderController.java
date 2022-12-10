package com.example.order.controller;

import com.example.order.dto.*;
import com.example.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
@RequestMapping(value = "/api/v1")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService=orderService;
    }

    @RequestMapping(value = "/orderByTable",method = RequestMethod.GET)
    @ResponseBody
    public OrderInfoDto getOrderByTableId(@RequestParam BigInteger table)
    {
        return orderService.getOrderByTable(table);
    }

    @RequestMapping(value = "/orderById",method = RequestMethod.GET)
    @ResponseBody
    public OrderInfoDto getOrderByTableId(@RequestParam String order_id)
    {
        return orderService.getOrderById(order_id);
    }

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    @ResponseBody
    public AllOrderInfo getAllOrder()
    {
        return orderService.getAllOrder();
    }

    @RequestMapping(value = "/orderDish",method = RequestMethod.GET)
    @ResponseBody
    public AllOrderDishInfo getAllDishInOrder(@RequestBody OrderByIdQuery query)
    {
        System.out.println(query.getOrder_id());
        return orderService.getAllDishInOrder(query);
    }

    @RequestMapping(value = "/order", method = RequestMethod.PUT)
    public ResponseEntity setOrderStatus(@RequestBody ChangeOrderInfoRequest request)
    {
        System.out.println(request);

        if(orderService.setOrderStatus(request))
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
