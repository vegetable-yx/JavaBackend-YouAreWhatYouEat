package com.example.order.service.impl;

import com.example.order.dto.OrderByTableQuery;
import com.example.order.dto.OrderInfoDto;
import com.example.order.entitiy.DishorderlistEntity;
import com.example.order.entitiy.OrderlistEntity;
import com.example.order.repository.DishOrderListRepository;
import com.example.order.repository.OrderListRepository;
import com.example.order.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderListRepository orderListRepository;
    @Resource
    private DishOrderListRepository dishOrderListRepository;

    @Override
    public OrderInfoDto getOrderByTable(OrderByTableQuery query)
    {
        BigInteger tableId=query.getTable();
        List<OrderlistEntity> allOrderList=orderListRepository.findAllByTableId(tableId);

        OrderlistEntity latest=null;
        for (OrderlistEntity orderListEntity:allOrderList
             ) {
            if(latest==null||latest.getCreationTime().before(orderListEntity.getCreationTime()))
            {
                latest=orderListEntity;
            }
        }

        OrderInfoDto result=new OrderInfoDto();
        if(latest!=null)
        {
            result.setOrder_id(latest.getOrderId());
            result.setTable_id(latest.getTableId().toString());
            result.setCreation_time(latest.getCreationTime().toString());
            result.setOrder_status(latest.getOrderStatus());

            //根据orderList找全部dishOrder算Price
            Double price=0d;
            List<DishorderlistEntity> allDish=dishOrderListRepository.findAllByOrderId(latest.getOrderId());
            for (DishorderlistEntity dish:allDish
                 ) {
                price+=dish.getFinalPayment();
            }
            result.setTotal_price(price);

            return result;
        }
        else
        {
            return null;
        }
    }
}
