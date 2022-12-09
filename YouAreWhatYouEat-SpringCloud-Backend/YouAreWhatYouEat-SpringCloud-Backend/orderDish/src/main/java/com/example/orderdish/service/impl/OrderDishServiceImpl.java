package com.example.orderdish.service.impl;

import com.example.orderdish.dto.DishInfo;
import com.example.orderdish.entity.DishorderlistEntity;
import com.example.orderdish.repository.DishOrderListRepository;
import com.example.orderdish.repository.DishPictureRepository;
import com.example.orderdish.repository.DishRepository;
import com.example.orderdish.repository.OrderListRepository;
import com.example.orderdish.service.OrderDishDervice;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderDishServiceImpl implements OrderDishDervice {
    @Resource
    private DishOrderListRepository dishOrderListRepository;

    @Resource
    private DishRepository dishRepository;

    @Resource
    private OrderListRepository orderListRepository;

    //这个是自己写的所以不用注解，而且要初始化
    private DishPictureRepository dishPictureRepository=new DishPictureRepository();

    @Override
    public List<DishInfo> getDishInfoInOrder(String orderId)
    {
        List<DishInfo> result=new ArrayList<DishInfo>();

        List<DishorderlistEntity> dishorderlistEntities=dishOrderListRepository.findAllByOrderId(orderId);
        for (DishorderlistEntity dishOrder:dishorderlistEntities
             ) {

            boolean match=false;
            for (DishInfo info:result
                 ) {
                if(info.getDish_id()==dishOrder.getDishId())
                {
                    info.setDish_num(BigInteger.valueOf(info.getDish_num().intValue()+1));
                    match=true;
                    break;
                }
            }

            if(!match)
            {
                DishInfo info =new DishInfo();
                info.setDish_id(dishOrder.getDishId());
                info.setDish_name(dishRepository.findByDishId(dishOrder.getDishId()).getDishName());
                info.setDish_status(dishOrder.getDishStatus());
                info.setDish_price(dishOrder.getFinalPayment());
                info.setDish_num(BigInteger.valueOf(1));
                info.setDish_picture(dishPictureRepository.getUrl()+info.getDish_id()+".png");

                result.add(info);
            }
        }

        return result;
    }
}
