package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllOrderInfo {
    private OrderSummary summary;
    private List<OrderInfoDto> orders=new ArrayList<OrderInfoDto>();

    public void addOrderInfo(OrderInfoDto info)
    {
        orders.add(info);
    }
}
