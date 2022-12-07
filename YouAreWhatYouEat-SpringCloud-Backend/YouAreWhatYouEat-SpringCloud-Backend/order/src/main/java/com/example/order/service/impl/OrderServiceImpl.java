package com.example.order.service.impl;

import com.example.order.dto.*;
import com.example.order.entitiy.DishorderlistEntity;
import com.example.order.entitiy.OrderlistEntity;
import com.example.order.repository.DishOrderListRepository;
import com.example.order.repository.OrderListRepository;
import com.example.order.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    @Override
    public AllOrderInfo getAllOrder()
    {
        AllOrderInfo result=new AllOrderInfo();
        OrderSummary summary=new OrderSummary();

        List<OrderlistEntity> allOrderList=orderListRepository.findAll();
        for (OrderlistEntity orderListEntity:allOrderList
        ) {
            OrderInfoDto info=new OrderInfoDto();
            info.setOrder_id(orderListEntity.getOrderId());
            info.setTable_id(orderListEntity.getTableId().toString());
            info.setCreation_time(orderListEntity.getCreationTime().toString());
            info.setOrder_status(orderListEntity.getOrderStatus());

            Double price=0d;
            List<DishorderlistEntity> allDish=dishOrderListRepository.findAllByOrderId(orderListEntity.getOrderId());
            for (DishorderlistEntity dish:allDish
            ) {
                price+=dish.getFinalPayment();
            }
            info.setTotal_price(price);

            result.addOrderInfo(info);

            //Summary
            summary.setOrder_count(BigInteger.valueOf(summary.getOrder_count().intValue()+1));
            switch(info.getOrder_status())
            {
                case "待处理":
                {
                    summary.setAwating_count(BigInteger.valueOf(summary.getAwating_count().intValue()+1));
                    summary.setAwating_credit(summary.getAwating_credit()+info.getTotal_price());
                    break;
                }
                case "制作中":
                {
                    summary.setProcessing_count(BigInteger.valueOf(summary.getProcessing_count().intValue()+1));
                    summary.setProcessing_credit(summary.getProcessing_credit()+info.getTotal_price());
                    break;
                }
                case "已完成":
                {
                    summary.setCompleted_count(BigInteger.valueOf(summary.getCompleted_count().intValue()+1));
                    summary.setCompleted_credit(summary.getCompleted_credit()+info.getTotal_price());
                    break;
                }
                case "已支付":
                {
                    summary.setPayed_count(BigInteger.valueOf(summary.getPayed_count().intValue()+1));
                    summary.setPayed_credit(summary.getPayed_credit()+info.getTotal_price());
                    break;
                }
            }
            summary.setTotal_credit(summary.getTotal_credit()+info.getTotal_price());

            if(orderListEntity.getCreationTime().toLocalDate()== LocalDate.now())
            {
                summary.setToday_credit(summary.getToday_credit()+info.getTotal_price());
            }

        }

        result.setSummary(summary);
        return result;
    }

    @Override
    public boolean setOrderStatus(ChangeOrderInfoRequest request)
    {
        OrderlistEntity orderlistEntity=new OrderlistEntity();
        orderlistEntity.setOrderStatus(request.getOrder_status());
        orderlistEntity.setOrderId(request.getOrder_id());
        orderlistEntity.setTableId(request.getTable_id());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            orderlistEntity.setCreationTime((Date) formatter.parse(request.getCreation_time()));
        }
        catch (Exception e)
        {
            System.out.println("Date parse error");
            orderlistEntity.setCreationTime(orderListRepository.findByOrderId(request.getOrder_id()).getCreationTime());
        }


        OrderlistEntity tem = orderListRepository.findByOrderId(request.getOrder_id());
        if (tem==null) return false;

        try {
            orderListRepository.saveAndFlush(orderlistEntity);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public AllOrderDishInfo getAllDishInOrder(OrderByIdQuery query)
    {
        AllOrderDishInfo result=new AllOrderDishInfo();
        List<DishorderlistEntity> dishorderlistEntities=dishOrderListRepository.findAllByOrderId(query.getOrder_id());

        Double totalPrice=0d;
        for (DishorderlistEntity dish:dishorderlistEntities
             ) {
            DishInfoDto dishInfoDto=new DishInfoDto();
            dishInfoDto.setDish_order_id(dish.getDishOrderId());
            dishInfoDto.setOrder_id(dish.getOrderId());
            dishInfoDto.setDish_id(dish.getDishId());
            dishInfoDto.setFinal_payment(dish.getFinalPayment());

            //TODO 要从另一个表获取原始价格
            dishInfoDto.setOriginal_price(dish.getFinalPayment());
            dishInfoDto.setDish_status(dish.getDishStatus());

            totalPrice+=dish.getFinalPayment();

            result.addDish(dishInfoDto);
        }
        result.getSummary().setTotal_price(totalPrice);

        return result;
    }
}
