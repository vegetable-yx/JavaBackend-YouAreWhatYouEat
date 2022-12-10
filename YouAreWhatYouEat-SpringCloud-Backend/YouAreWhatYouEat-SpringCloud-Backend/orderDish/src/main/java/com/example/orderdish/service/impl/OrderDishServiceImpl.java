package com.example.orderdish.service.impl;

import com.example.orderdish.dto.*;
import com.example.orderdish.dto.submitcommentdish.CommentDishSubmit;
import com.example.orderdish.dto.submitcommentservice.CommentServiceSubmit;
import com.example.orderdish.dto.submitorder.AddOrderSubmit;
import com.example.orderdish.dto.submitorder.DishesInfo;
import com.example.orderdish.dto.submitorder.OrderSubmit;
import com.example.orderdish.dto.submitorder.ResponseOrder;
import com.example.orderdish.entity.*;
import com.example.orderdish.repository.*;
import com.example.orderdish.service.OrderDishService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderDishServiceImpl implements OrderDishService {
    @Resource
    private DishOrderListRepository dishOrderListRepository;

    @Resource
    private DishRepository dishRepository;

    @Resource
    private OrderListRepository orderListRepository;

    @org.springframework.beans.factory.annotation.Value("${myConfiguration.url}")
    private String imageBase;

    @Resource
    private DishTagRepository dishTagRepository;

    @Resource
    private DishHasTagRepository dishHasTagRepository;

    @Resource
    private DishCommentRepository dishCommentRepository;

    @Resource
    private PromotionRepository promotionRepository;

    @Resource
    private TableRepository tableRepository;

    @Resource
    private ServiceCommentRepository serviceCommentRepository;

    @Resource
    private VIPRepository vipRepository;

    @Override
    public DishInfoSum getDishInfoInOrder(String orderId)
    {
        DishInfoSum result=new DishInfoSum();

        List<DishorderlistEntity> dishorderlistEntities=dishOrderListRepository.findAllByOrderId(orderId);
        for (DishorderlistEntity dishOrder:dishorderlistEntities
             ) {

            boolean match=false;
            for (DishInfo info:result.getDish_info()
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
                info.setDish_picture(imageBase+"dishes/dish_"+info.getDish_id()+".png");

                result.addDish(info);
            }
        }

        return result;
    }

    @Override
    public List<DishInfo2> getDishByCategory(String tag, BigInteger promotionId)
    {
        List<DishInfo2> result=new ArrayList<DishInfo2>();

        if(tag==null)
        {
            return result;
        }

        DishtagsEntity tagEntity=dishTagRepository.findByDtagName(tag);
        if(tagEntity==null)
        {
            return result;
        }

        List<DishHasTagEntity> dishesId=dishHasTagRepository.findByDtagId(tagEntity.getDtagId());
        for (DishHasTagEntity id:dishesId
             ) {
            DishesEntity dish=dishRepository.findByDishId(id.getDishId());

            DishInfo2 info=new DishInfo2();
            info.setDish_id(dish.getDishId());
            info.setDish_name(dish.getDishName());
            info.setDish_picture(imageBase+"dishes/dish_"+info.getDish_id()+".png");
            info.setDish_price(dish.getDishPrice());
            info.setDish_description(dish.getDishDescription());

            Double discount=1d;
            PromotionEntity promotion = promotionRepository.findByPromotionId(promotionId);
            if(promotion!=null)
            {
                for (HasdishEntity hasDish:promotion.getHasdishesByPromotionId()
                ) {
                    if(hasDish.getDishId()==info.getDish_id())
                    {
                        discount=hasDish.getDiscount();
                        break;
                    }
                }
            }

            info.setDish_discount(discount);


            Double rate=0d;
            List<CommentOnDishEntity> commentOnDishEntities=dishCommentRepository.findAllByDishId(info.getDish_id());
            for (CommentOnDishEntity comment:commentOnDishEntities
                 ) {
                rate+=comment.getStars();
            }
            if(commentOnDishEntities.size()==0||rate==0)
            {
                rate=5d;
            }
            else
            {
                rate=rate/commentOnDishEntities.size();
            }

            info.setDish_rate(rate);
        }

        return result;
    }

    @Override
    public OrderTotalPrice getOrderTotalPrice(String orderId)
    {
        OrderTotalPrice result=new OrderTotalPrice();

        Double total=0d;
        List<DishorderlistEntity> dishorderlistEntities=dishOrderListRepository.findAllByOrderId(orderId);
        for (DishorderlistEntity dish:dishorderlistEntities
             ) {
            total+=dish.getFinalPayment();
        }

        result.setOrderTotalPrice(total);

        return result;
    }

    @Override
    public OrderStatus getOrderStatus(String orderId)
    {
        OrderStatus result=new OrderStatus();

        OrderlistEntity orderlistEntity=orderListRepository.findByOrderId(orderId);
        if(orderlistEntity!=null)
        {
            result.setOrder_status(orderlistEntity.getOrderStatus());
        }
        else
        {
            result.setOrder_status("待处理");
        }

        return result;
    }

    @Override
    public List<PromotionInfo> getAllPromotion()
    {
        List<PromotionInfo> result=new ArrayList<PromotionInfo>();

        List<PromotionEntity> pros=promotionRepository.findAll();
        for (PromotionEntity pro:pros
             ) {

            if(pro.getStartTime().before(new Date(System.currentTimeMillis()))
                && pro.getEndTime().after(new Date(System.currentTimeMillis())))
            {

            }
            else
            {
                continue;
            }

            PromotionInfo promotionInfo=new PromotionInfo();

            promotionInfo.setPromotion_id(pro.getPromotionId());
            promotionInfo.setDescription(pro.getDescription());
            promotionInfo.setPicture(imageBase+"promotions/promotion_"+pro.getPromotionId()+".png");

            for (HasdishEntity hasDish:pro.getHasdishesByPromotionId())
            {
                PromotionDishInfo promotionDishInfo=new PromotionDishInfo();
                PromotionDishInfo_sub promotionDishInfo_sub=new PromotionDishInfo_sub();

                DishesEntity dishesEntity=dishRepository.findByDishId(hasDish.getDishId());

                promotionDishInfo_sub.setDish_id(dishesEntity.getDishId());
                promotionDishInfo_sub.setDish_name(dishesEntity.getDishName());
                promotionDishInfo_sub.setDish_price(dishesEntity.getDishPrice());
                promotionDishInfo_sub.setDish_description(dishesEntity.getDishDescription());
                promotionDishInfo_sub.setDish_picture(imageBase+"dishes/dish_"+dishesEntity.getDishId()+".png");

                promotionDishInfo.setDish(promotionDishInfo_sub);
                promotionDishInfo.setDiscount(hasDish.getDiscount());

                promotionInfo.addPromotionDishInfo(promotionDishInfo);
            }

            result.add(promotionInfo);
        }

        return result;
    }

    @Override
    public DishAllInfoSum getDishAllInfo()
    {
        DishAllInfoSum result=new DishAllInfoSum();

        List<DishesEntity> dishesEntities=dishRepository.findAll();
        for (DishesEntity dish:dishesEntities
             ) {
            DishAllInfo dishAllInfo=new DishAllInfo();
            dishAllInfo.setDish_id(dish.getDishId());
            dishAllInfo.setDish_name(dish.getDishName());
            dishAllInfo.setDish_picture(imageBase+"dishes/dish_"+dish.getDishId()+".png");
            dishAllInfo.setDish_video(dish.getVideo());
            dishAllInfo.setDish_price(dish.getDishPrice());
            dishAllInfo.setDish_description(dish.getDishDescription());

            Double rate=0d;
            List<CommentOnDishEntity> commentOnDishEntities=dishCommentRepository.findAllByDishId(dish.getDishId());
            for (CommentOnDishEntity comment:commentOnDishEntities
            ) {
                rate+=comment.getStars();
            }
            if(commentOnDishEntities.size()==0||rate==0)
            {
                rate=5d;
            }
            else
            {
                rate=rate/commentOnDishEntities.size();
            }

            dishAllInfo.setDish_rate(rate);

            //TODO 一个comment一个sell

            for (DishHasTagEntity dishHasTag:dishHasTagRepository.findAllByDishId(dish.getDishId())
                 ) {
                dishAllInfo.getDish_tag().add(dishTagRepository.findByDtagId(dishHasTag.getDtagId()).getDtagName());
            }

            for (CommentOnDishEntity comment:dishCommentRepository.findAllByDishId(dish.getDishId())
                 ) {
                DishComment com=new DishComment();
                com.setComment_content(comment.getCommentContent());
                com.setComment_star(comment.getStars());
                com.setComment_time(comment.getCommentTime().toString());

                dishAllInfo.getDish_comment().add(com);
            }

            dishAllInfo.setDish_sell_num(BigInteger.valueOf(dishAllInfo.getDish_comment().size()));

            result.getDish_all().add(dishAllInfo);

        }

        return result;
    }

    @Override
    public DishRealPrice getDishRealPrice(BigInteger promotionId, BigInteger dishId)
    {
        DishRealPrice result=new DishRealPrice();

        DishesEntity dish=dishRepository.findByDishId(dishId);

        result.setPrice(dish.getDishPrice());

        Double discount=1d;
        PromotionEntity promotion = promotionRepository.findByPromotionId(promotionId);
        if(promotion!=null)
        {
            for (HasdishEntity hasDish:promotion.getHasdishesByPromotionId()
            ) {
                if(hasDish.getDishId()==dishId)
                {
                    discount=hasDish.getDiscount();
                    break;
                }
            }
        }

        result.setDiscount(discount);

        return result;
    }

    @Override
    public ResponseOrder submitOrder(OrderSubmit submit)
    {
        ResponseOrder result=new ResponseOrder();

        OrderlistEntity orderlistEntity=new OrderlistEntity();
        orderlistEntity.setOrderStatus("待处理");
        orderlistEntity.setTableId(submit.getTable_id());
        orderlistEntity.setCreationTime(new Date(System.currentTimeMillis()));

        String order_id = "";

        do {
            Random random = new Random();
            order_id = "";

            for (int i = 0; i < 11; i++)
            {
                Integer r = random.nextInt(0, 62);
                if (r < 10) order_id += r.toString();
                else if (r < 36) order_id += (char)(97 + r - 10);
                else order_id += (char)(65 + r - 36);
            }
        }while(orderListRepository.findByOrderId(order_id)!=null);

        orderlistEntity.setOrderId(order_id);

        result.setOrder_id(order_id);

        try {
            orderListRepository.saveAndFlush(orderlistEntity);
        } catch (Exception e) {
            System.out.println("Create order error!");
            return null;
        }

        for (DishesInfo dish: submit.getDishes_info()
             ) {
            DishorderlistEntity dishorderlistEntity=new DishorderlistEntity();

            dishorderlistEntity.setOrderId(order_id);
            dishorderlistEntity.setDishId(dish.getDish_id());
            dishorderlistEntity.setFinalPayment(dish.getDish_price_to_pay());
            dishorderlistEntity.setDishStatus("待处理");
            dishorderlistEntity.setRemark(dish.getRemark());

            String dish_order_id = "";

            do {
                Random random = new Random();
                dish_order_id = "";

                for (int i = 0; i < 11; i++)
                {
                    Integer r = random.nextInt(0, 62);
                    if (r < 10) dish_order_id += r.toString();
                    else if (r < 36) dish_order_id += (char)(97 + r - 10);
                    else dish_order_id += (char)(65 + r - 36);
                }
            }while(dishOrderListRepository.findByDishOrderId(dish_order_id)!=null);

            dishorderlistEntity.setDishOrderId(dish_order_id);

            try {
                dishOrderListRepository.saveAndFlush(dishorderlistEntity);
            } catch (Exception e) {
                System.out.println("Create dish_order error!");
                return null;
            }

        }

        return result;
    }

    @Override
    public boolean setTableStatus(BigInteger id){

        DinningtableEntity tem = tableRepository.findByTableId(id);
        if (tem==null) return false;

        try {
            tem.setOccupied("否");
            tableRepository.saveAndFlush(tem);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addDishComment(CommentDishSubmit submit)
    {
        CommentOnDishEntity commentOnDishEntity=new CommentOnDishEntity();

        String comment_id="";
        do {
            Random random = new Random();
            comment_id = "";

            for (int i = 0; i < 11; i++)
            {
                Integer r = random.nextInt(0, 62);
                if (r < 10) comment_id += r.toString();
                else if (r < 36) comment_id += (char)(97 + r - 10);
                else comment_id += (char)(65 + r - 36);
            }
        }while(dishCommentRepository.findByCommentId(comment_id)!=null);
        commentOnDishEntity.setCommentId(comment_id);

        commentOnDishEntity.setUserName(submit.getUsername());
        commentOnDishEntity.setDishId(submit.getDish_id());
        commentOnDishEntity.setStars(submit.getRate());
        commentOnDishEntity.setCommentContent(submit.getContent());
        commentOnDishEntity.setCommentTime(new Timestamp(System.currentTimeMillis()));

        try {
            dishCommentRepository.saveAndFlush(commentOnDishEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addServiceComment(CommentServiceSubmit submit)
    {
        CommentOnServiceEntity commentOnServiceEntity=new CommentOnServiceEntity();

        String comment_id="";
        do {
            Random random = new Random();
            comment_id = "";

            for (int i = 0; i < 11; i++)
            {
                Integer r = random.nextInt(0, 62);
                if (r < 10) comment_id += r.toString();
                else if (r < 36) comment_id += (char)(97 + r - 10);
                else comment_id += (char)(65 + r - 36);
            }
        }while(serviceCommentRepository.findByCommentId(comment_id)!=null);
        commentOnServiceEntity.setCommentId(comment_id);

        commentOnServiceEntity.setUserName(submit.getUsername());
        commentOnServiceEntity.setStars(submit.getRate());
        commentOnServiceEntity.setCommentContent(submit.getContent());
        commentOnServiceEntity.setCommentTime(new Timestamp(System.currentTimeMillis()));

        try {
            serviceCommentRepository.saveAndFlush(commentOnServiceEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean submitAddOrder(AddOrderSubmit submit)
    {
        if(orderListRepository.findByOrderId(submit.getOrder_id())==null)
        {
            return false;
        }

        for (DishesInfo dish: submit.getDishes_info()
        ) {
            DishorderlistEntity dishorderlistEntity=new DishorderlistEntity();

            dishorderlistEntity.setOrderId(submit.getOrder_id());
            dishorderlistEntity.setDishId(dish.getDish_id());
            dishorderlistEntity.setFinalPayment(dish.getDish_price_to_pay());
            dishorderlistEntity.setDishStatus("待处理");
            dishorderlistEntity.setRemark(dish.getRemark());

            String dish_order_id = "";

            do {
                Random random = new Random();
                dish_order_id = "";

                for (int i = 0; i < 11; i++)
                {
                    Integer r = random.nextInt(0, 62);
                    if (r < 10) dish_order_id += r.toString();
                    else if (r < 36) dish_order_id += (char)(97 + r - 10);
                    else dish_order_id += (char)(65 + r - 36);
                }
            }while(dishOrderListRepository.findByDishOrderId(dish_order_id)!=null);

            dishorderlistEntity.setDishOrderId(dish_order_id);

            try {
                dishOrderListRepository.saveAndFlush(dishorderlistEntity);
            } catch (Exception e) {
                System.out.println("Create dish_order error!");
                return false;
            }

        }

        return true;
    }

    @Override
    public boolean updateCredit(UpdateUserCredit submit)
    {
        if(vipRepository.findByUserName(submit.getUser_name())==null)
        {
            return false;
        }

        VipEntity vipEntity=vipRepository.findByUserName(submit.getUser_name());
        vipEntity.setCredit(submit.getCredit());

        try {
            vipRepository.saveAndFlush(vipEntity);
        } catch (Exception e) {
            System.out.println("Update vip error!");
            return false;
        }

        return true;
    }
}
