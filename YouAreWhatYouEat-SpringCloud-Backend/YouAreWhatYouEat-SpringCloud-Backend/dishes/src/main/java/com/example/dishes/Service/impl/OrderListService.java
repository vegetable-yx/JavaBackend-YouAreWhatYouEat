package com.example.dishes.Service.impl;


import com.example.dishes.Entity.DishorderlistEntity;
import com.example.dishes.Entity.OrderlistEntity;
import com.example.dishes.Repository.*;
import com.example.dishes.dto.Dish.PutDishItem;
import com.example.dishes.dto.List.GetOrderListItem;
import com.example.dishes.dto.List.OrderDishItem;
import com.example.dishes.dto.List.PatchUpdateDishStatus;
import com.example.dishes.dto.List.PatchUpdateOrderStatus;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            if(x.getOrder_id()==item.getOrderId()){
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
                orderDishItem.setDish_order_id(dishOrder.getDishOrderId());
                orderDishItem.setRemark(dishOrder.getRemark());
                orderDishItem.setStatus(dishOrder.getDishStatus());
                orderDishItem.setDish_name(dishesRepository.findDishNameByDishId(dishOrder.getDishId()).get(0));
                GetOrderListItem item=new GetOrderListItem();

                List<OrderDishItem> x=result.get(orderAlreadyAdd(result,dishOrder)).getDish();

                if(x!=null)
                {
                    x.add(orderDishItem);
                    item.setDish(x);
                }

                item.setOrder_id(result.get(orderAlreadyAdd(result,dishOrder)).getOrder_id());
                item.setOrder_status(result.get(orderAlreadyAdd(result,dishOrder)).getOrder_status());

                //把新的填进去
                result.set(orderAlreadyAdd(result,dishOrder),item);

            }
            else{
                GetOrderListItem item=new GetOrderListItem();
                item.setOrder_id(dishOrder.getOrderId());
                item.setOrder_status(orderListRepository.findOrderStatusById(dishOrder.getOrderId()).get(0));
                OrderDishItem orderDishItem=new OrderDishItem();
                orderDishItem.setDish_order_id(dishOrder.getDishOrderId());
                orderDishItem.setRemark(dishOrder.getRemark());
                orderDishItem.setStatus(dishOrder.getDishStatus());
                orderDishItem.setDish_name(dishesRepository.findDishNameByDishId(dishOrder.getDishId()).get(0));
                List<OrderDishItem> x=new ArrayList<>();

                if(x!=null)
                {
                    x.add(orderDishItem);
                    item.setDish(x);
                }

                result.add(item);
            }
        }

        return result;
    }
    @Transactional
    @Override
    public HttpStatus putUpdateDishStatus(PatchUpdateDishStatus item) {

    if(dishOrderListRepository.findPayIdByDishOrderId(item.getDishOrderId())==null){
        return HttpStatus.BAD_REQUEST;
    }
    DishorderlistEntity dishorderlistEntity=new DishorderlistEntity();
    dishorderlistEntity.setRemark(dishOrderListRepository.findRemarkIdByDishOrderId(item.getDishOrderId()).get(0));
    dishorderlistEntity.setDishId(dishOrderListRepository.findDishIdByDishOrderId(item.getDishOrderId()).get(0));
    dishorderlistEntity.setOrderId(dishOrderListRepository.findOrderIdByDishOrderId(item.getDishOrderId()).get(0));
    dishorderlistEntity.setFinalPayment(dishOrderListRepository.findPayIdByDishOrderId(item.getDishOrderId()).get(0).intValue());
    dishorderlistEntity.setDishOrderId(item.getDishOrderId());
    dishorderlistEntity.setDishStatus(item.getDishStatus());
        try {
            dishOrderListRepository.saveAndFlush( dishorderlistEntity);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }

    }
    @Transactional
    @Override
    public HttpStatus putUpdateOrderStatus(PatchUpdateOrderStatus item) {
        if(orderListRepository.findOrderStatusById(item.getOrderId())==null){
            return HttpStatus.BAD_REQUEST;
        }
        OrderlistEntity orderlistEntity=new OrderlistEntity();
        orderlistEntity.setOrderId(item.getOrderId());
        orderlistEntity.setOrderStatus(item.getOrderStatus());
        orderlistEntity.setCreationTime(orderListRepository.findCreationTimeById(item.getOrderId()).get(0));
        orderlistEntity.setTableId(orderListRepository.findTableIdById(item.getOrderId()).get(0));
        try {
            orderListRepository.saveAndFlush(orderlistEntity);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }

    }
}
