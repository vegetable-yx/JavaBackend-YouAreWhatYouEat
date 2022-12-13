package com.example.pay.controller;

import com.example.pay.dto.OrderStatusOutDto;
import com.example.pay.dto.PayQROutDto;
import com.example.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")

public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public ResponseEntity<PayQROutDto> pay(
            @RequestParam(required = true) String order_id,
            @RequestParam(required = true) Double final_price
    )
    {
        PayQROutDto result = new PayQROutDto();
        try {
            result = payService.pay(order_id, final_price);
            if (result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "orderPayStatus", method = RequestMethod.GET)
    public ResponseEntity<OrderStatusOutDto> getOrderPayStatus(
            @RequestParam(required = true) String order_id
    )
    {
        OrderStatusOutDto result = payService.getOrderPayStatus(order_id);
        if (result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
