package com.example.orderlists.Service.impl;

import com.example.orderlists.Entity.DishorderlistEntity;
import com.example.orderlists.Entity.HasdishEntity;
import com.example.orderlists.Entity.OrderlistEntity;
import com.example.orderlists.Entity.PromotionEntity;
import com.example.orderlists.Repository.*;
import com.example.orderlists.Service.OrderListService;
import com.example.orderlists.dto.GetOrders;
import com.example.orderlists.dto.Order;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
@Slf4j
@Service
public class OdLsServiceImpl implements OrderListService {

    @Resource
    OdLsRepository odLsRepository;

    @Resource
    DsOdLsRepository dsOdLsRepository;

    @Resource
    PromotionRepository promotionRepository;

    @Resource
    HasdisRepository hasdisRepository;

    @Resource
    DishesRepository dishesRepository;

    @Override
    public GetOrders getOders(String start, String end) {


        System.out.println(start);
        System.out.println(end);
        Date start_time ,end_time;

        start_time=new Date(Long.MIN_VALUE);
        end_time=new Date(Long.MAX_VALUE);




        List<OrderlistEntity> ls=odLsRepository.findAll();



        List<OrderlistEntity> orderlistEntities=new ArrayList<>();

        List<PromotionEntity> ps=promotionRepository.findAll();

        //根据时间过滤 出来所有订单
        for (OrderlistEntity item:ls){
            if(start_time.compareTo(item.getCreationTime())<=0&&end_time.compareTo(item.getCreationTime())>=0){
                orderlistEntities.add(item);
            }
        }



        GetOrders result=new GetOrders();

        List<Order> orders=new ArrayList<>();

        int totalPay=0;



        //对每个订单进行计算
        for (OrderlistEntity item:orderlistEntities){

            Order order=new Order();
            order.setOrderId(item.getOrderId());
            order.setOrderStatus(item.getOrderStatus());
            order.setCreationTime(item.getCreationTime().toString());
            order.setTableId(item.getTableId().toString());

            int finalPay=0;

            List<DishorderlistEntity> dishorderlistEntities=dsOdLsRepository.findAllByOrderId(item.getOrderId());


            //得到点菜表
            for(DishorderlistEntity d:dishorderlistEntities){
                finalPay+=d.getFinalPayment();
            }
            order.setFinalPay(finalPay);

            totalPay+=finalPay;

            List<PromotionEntity> promotionEntities=new ArrayList<>();
            //把这个订单所享受的活动,按照时间筛选出来
            for(PromotionEntity promotion:ps){
                if(item.getCreationTime().compareTo(promotion.getStartTime())>=0
                        &&
                        item.getCreationTime().compareTo(promotion.getEndTime())<=0)
                {
                    promotionEntities.add(promotion);
                }
            }

            Dictionary<BigInteger,Integer> discountDic=new Hashtable<>();

            List<HasdishEntity> hasdishEntities=new ArrayList<>();

            //得到享受的所有活动，所有的DISH折扣
            for(PromotionEntity promotion:promotionEntities){
                List<HasdishEntity> hasDishLs=hasdisRepository.findAllByPromotionId(promotion.getPromotionId());
                hasdishEntities.addAll(hasDishLs);
            }
            //把这个东西 放进字典
            for(HasdishEntity hasdish:hasdishEntities){
                discountDic.put(hasdish.getDishId(),hasdish.getDiscount());
            }

            int discountPrice=0;


            for(DishorderlistEntity d:dishorderlistEntities){
                if(discountDic.get(d.getDishId())!=null){
                    discountPrice+=dishesRepository.getPriceById(d.getDishId())*discountDic.get(d.getDishId());
                }
            }

            order.setDiscountPrice(discountPrice);



            orders.add(order);


        }

        result.setOrders(orders);

        Dictionary<String,Integer> dc1=new Hashtable<>();
        dc1.put("order_cunt",orderlistEntities.size());
        Dictionary<String,Integer> dc2=new Hashtable<>();
        dc2.put("total_credit",totalPay);
        List<Dictionary> dicLs=new ArrayList<>();
        dicLs.add(dc1);
        dicLs.add(dc2);

        result.setSummary(dicLs);
        result.setOrders(orders);
        if(orderlistEntities.size()==0){
            result.setErrorCode(404);
        }else {
            result.setErrorCode(200);
        }

        return result;
    }
}
