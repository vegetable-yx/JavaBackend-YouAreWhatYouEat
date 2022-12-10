package com.example.orderdish.service;

import com.example.orderdish.dto.*;
import com.example.orderdish.dto.submitcommentdish.CommentDishSubmit;
import com.example.orderdish.dto.submitcommentservice.CommentServiceSubmit;
import com.example.orderdish.dto.submitorder.AddOrderSubmit;
import com.example.orderdish.dto.submitorder.OrderSubmit;
import com.example.orderdish.dto.submitorder.ResponseOrder;

import java.math.BigInteger;
import java.util.List;

public interface OrderDishService {
    DishInfoSum getDishInfoInOrder(String orderId);

    List<DishInfo2> getDishByCategory(String tag, BigInteger promotionId);

    OrderTotalPrice getOrderTotalPrice(String orderId);

    OrderStatus getOrderStatus(String orderId);

    List<PromotionInfo> getAllPromotion();

    DishAllInfoSum getDishAllInfo();

    DishRealPrice getDishRealPrice(BigInteger promotionId, BigInteger dishId);

    ResponseOrder submitOrder(OrderSubmit submit);

    boolean setTableStatus(BigInteger id);

    boolean addDishComment(CommentDishSubmit submit);

    boolean addServiceComment(CommentServiceSubmit submit);

    boolean submitAddOrder(AddOrderSubmit submit);

    boolean updateCredit(UpdateUserCredit submit);
}
