package com.example.dishes.Service.impl;


import com.example.dishes.Entity.DishorderlistEntity;
import com.example.dishes.Repository.*;
import com.example.dishes.dto.Dish.PutDishItem;
import com.example.dishes.dto.List.GetOrderListItem;
import com.example.dishes.dto.List.OrderDishItem;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderListService implements com.example.dishes.Service.OrderListService {

    @Resource
    private DishesRepository dishesRepository;

    @Resource
    private DishTagsRepository dishTagsRepository;

    @Resource
    private IngredientsRepository ingredientsRepository;

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private DishHasTagRepository dishHasTagRepository;

    @Resource
    private DishNeedIngrRepository dishNeedIngrRepository;

    @Resource
    private OrderListRepository orderListRepository;

    @Resource
    private DishOrderListRepository dishOrderListRepository;

    private int orderAlreadyAdd(List<GetOrderListItem> result,DishorderlistEntity item){

        for(GetOrderListItem x:result){
            if(x.getOrderId()==item.getOrderId()){
                return result.indexOf(x);

            }
        }
        return -1;
    }

    @Override
    public List<GetOrderListItem> getOrderList() {


        List<DishorderlistEntity> allDishOrder=dishOrderListRepository.findAll();
        List<GetOrderListItem> result=new ArrayList<>();
        //遍历所有订单ID
        for(DishorderlistEntity dishOrder:allDishOrder){
            //该id已经添加过，只需要添加菜即可
            if(orderAlreadyAdd(result,dishOrder)!=-1){
                OrderDishItem orderDishItem=new OrderDishItem();
                orderDishItem.setDishOrderId(dishOrder.getDishOrderId());
                orderDishItem.setRemark(dishOrder.getRemark());
                orderDishItem.setDishStatus(dishOrder.getDishStatus());
                orderDishItem.setDishName(dishesRepository.findDishNameByDishId(dishOrder.getDishId()).get(0));
                GetOrderListItem item=new GetOrderListItem();

                List<OrderDishItem> x=result.get(orderAlreadyAdd(result,dishOrder)).getDishes();

                if(x!=null)
                {
                    x.add(orderDishItem);
                    item.setDishes(x);
                }

                item.setOrderId(result.get(orderAlreadyAdd(result,dishOrder)).getOrderId());
                item.setOrderStatus(result.get(orderAlreadyAdd(result,dishOrder)).getOrderStatus());

                //把新的填进去
                result.set(orderAlreadyAdd(result,dishOrder),item);

            }
            else{
                GetOrderListItem item=new GetOrderListItem();
                item.setOrderId(dishOrder.getOrderId());
                item.setOrderStatus(orderListRepository.findOrderStatusById(dishOrder.getOrderId()).get(0));
                OrderDishItem orderDishItem=new OrderDishItem();
                orderDishItem.setDishOrderId(dishOrder.getDishOrderId());
                orderDishItem.setRemark(dishOrder.getRemark());
                orderDishItem.setDishStatus(dishOrder.getDishStatus());
                orderDishItem.setDishName(dishesRepository.findDishNameByDishId(dishOrder.getDishId()).get(0));
                List<OrderDishItem> x=new ArrayList<>();

                if(x!=null)
                {
                    x.add(orderDishItem);
                    item.setDishes(x);
                }

                result.add(item);
            }
        }

        return result;
    }

    @Override
    public HttpStatus putUpdateDishStatus(PutDishItem item) {
        return null;
    }
}
