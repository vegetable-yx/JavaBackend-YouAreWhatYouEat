package com.example.orderlists.Service.impl;

import com.example.orderlists.Entity.*;
import com.example.orderlists.Repository.*;
import com.example.orderlists.Service.OrderListService;
import com.example.orderlists.dto.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
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

    @Resource
    DishTagsRepository dishTagsRepository;

    @Override
    public GetOrders getOders(String start, String end) {
        Timestamp start_time ,end_time;

        start_time=new Timestamp(Long.MIN_VALUE);
        end_time=new Timestamp(Long.MAX_VALUE);
        System.out.println(start_time);
        if (start!=null){
            start_time=Timestamp.valueOf(start);
        }
        if(end!=null)
        {
            end_time=Timestamp.valueOf(end);
        }

        System.out.println(start_time);


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
            order.setOrder_id(item.getOrderId());
            order.setOrderStatus(item.getOrderStatus());
            order.setCreation_time(item.getCreationTime().toString());
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

        Dictionary<String, Integer> dicLs=new Hashtable();
        dicLs.put("order_count",orderlistEntities.size());
        dicLs.put("total_credit",totalPay);


        result.setSummary(dicLs);
        result.setOrders(orders);
        if(orderlistEntities.size()==0){
            result.setErrorCode(404);
        }else {
            result.setErrorCode(200);
        }

        return result;
    }


    @Override
    public GetDishes getDishes(String start, String end) {
        List<DishorderlistEntity> ls=dsOdLsRepository.findAll();
        if(ls.size()==0){
            return null;
        }

        Timestamp start_time ,end_time;

        start_time=new Timestamp(Long.MIN_VALUE);
        end_time=new Timestamp(Long.MAX_VALUE);

        if (start!=null){
            start_time=Timestamp.valueOf(start);
        }
        if(end!=null)
        {
            end_time=Timestamp.valueOf(end);
        }

        GetDishes getDishes=new GetDishes();
        List<Dish> data=new ArrayList<>();
        for(DishorderlistEntity item:ls){

            if(odLsRepository.findFirstByOrderId(item.getOrderId()).getCreationTime().compareTo(start_time)<0
                    ||
                    odLsRepository.findFirstByOrderId(item.getOrderId()).getCreationTime().compareTo(end_time)>0
            ){
                continue;
            }


            Dish dish=new Dish();
            dish.setDishName(dishesRepository.findFirstByDishId(item.getDishId()).getDishName());
            dish.setDishId(item.getDishId());
            dish.setDishStatus(item.getDishStatus());
            dish.setDishOrderId(item.getDishOrderId());
            dish.setPay(BigInteger.valueOf(item.getFinalPayment()));
            dish.setCreationTime(odLsRepository.findFirstByOrderId(item.getOrderId()).getCreationTime());
            dish.setOrderId(item.getOrderId());

            data.add(dish);

        }

        getDishes.setCode(200);
        getDishes.setData(data);


        return getDishes;
    }


    @Override
    public GetDishNums getDishesNum(String start, String end) {

        List<DishorderlistEntity> ls=dsOdLsRepository.findAll();
        if(ls.size()==0){
            return null;
        }

        Timestamp start_time ,end_time;

        start_time=new Timestamp(Long.MIN_VALUE);
        end_time=new Timestamp(Long.MAX_VALUE);

        if (start!=null){
            start_time=Timestamp.valueOf(start);
        }
        if(end!=null)
        {
            end_time=Timestamp.valueOf(end);
        }
        GetDishNums getDishNums=new GetDishNums();
        List<DishNum > data=new ArrayList<>();
        for(DishorderlistEntity item:ls){
            if(odLsRepository.findFirstByOrderId(item.getOrderId()).getCreationTime().compareTo(start_time)<0
                    ||
                    odLsRepository.findFirstByOrderId(item.getOrderId()).getCreationTime().compareTo(end_time)>0
            ){
                continue;
            }
            DishNum dishNum=new DishNum();

            List<String> tags=new ArrayList<>();
            for(DishHasTagEntity dishHasTagEntity:dishesRepository.findFirstByDishId(item.getDishId()).getTags()){
                tags.add(dishTagsRepository.findFirstByDtagId(dishHasTagEntity.getDtagId()).getDtagName());
            }
            dishNum.setTags(tags);
            dishNum.setPrice(BigInteger.valueOf(dishesRepository.findFirstByDishId(item.getDishId()).getDishPrice()));
            dishNum.setDish_id(item.getDishId());
            dishNum.setName(dishesRepository.findFirstByDishId(item.getDishId()).getDishName());

            int num=0,totalPay=0;
            for(DishorderlistEntity count:ls){
                if(count.getDishId()==item.getDishId()){
                    num++;
                    totalPay+=count.getFinalPayment();
                }
            }
            dishNum.setOrder_times(num);
            dishNum.setTotal_credit(BigInteger.valueOf(totalPay));
            data.add(dishNum);
        }
        getDishNums.setCode(200);
        getDishNums.setData(data);
        return getDishNums;
    }
}
