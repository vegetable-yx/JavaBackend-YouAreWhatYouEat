package com.example.pay.service;


import com.example.pay.dto.OrderStatusOutDto;
import com.example.pay.dto.PayQROutDto;

public interface PayService {
    public PayQROutDto pay(String id, Double price);
    public OrderStatusOutDto getOrderPayStatus(String id);
}