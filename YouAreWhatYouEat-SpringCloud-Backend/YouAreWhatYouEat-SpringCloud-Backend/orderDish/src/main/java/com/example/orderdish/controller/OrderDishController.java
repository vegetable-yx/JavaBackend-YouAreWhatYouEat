package com.example.orderdish.controller;

import com.example.orderdish.dto.*;
import com.example.orderdish.dto.submitcommentdish.CommentDishSubmit;
import com.example.orderdish.dto.submitcommentservice.CommentServiceSubmit;
import com.example.orderdish.dto.submitorder.AddOrderSubmit;
import com.example.orderdish.dto.submitorder.OrderSubmit;
import com.example.orderdish.dto.submitorder.ResponseOrder;
import com.example.orderdish.service.OrderDishService;
import com.example.orderdish.service.impl.OrderDishServiceImpl;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1")
public class OrderDishController {
    private OrderDishService orderDishService;

    public OrderDishController(OrderDishService service)
    {
        this.orderDishService=service;
    }

    @RequestMapping(value = "/orderDishInfo",method = RequestMethod.GET)
    @ResponseBody
    public DishInfoSum getDishInfoInOrder(@RequestParam String order_id)
    {
        return orderDishService.getDishInfoInOrder(order_id);
    }

    @RequestMapping(value = "/dishesByCategory",method = RequestMethod.GET)
    @ResponseBody
    public List<DishInfo2> getDishByCategory(@RequestParam String dish_tag,@RequestParam BigInteger promotion_id)
    {
        return orderDishService.getDishByCategory(dish_tag,promotion_id);
    }

    @RequestMapping(value = "/orderPrice",method = RequestMethod.GET)
    @ResponseBody
    public OrderTotalPrice getOrderTotalPrice(@RequestParam String order_id)
    {
        return orderDishService.getOrderTotalPrice(order_id);
    }

    @RequestMapping(value = "/orderPayStatus",method = RequestMethod.GET)
    @ResponseBody
    public OrderStatus getOrderStatus(@RequestParam String order_id)
    {
        return orderDishService.getOrderStatus(order_id);
    }

    @RequestMapping(value = "/runningPromotion",method = RequestMethod.GET)
    @ResponseBody
    public List<PromotionInfo> getAllPromotion()
    {
        return orderDishService.getAllPromotion();
    }

    @RequestMapping(value = "/dishesForPromotions",method = RequestMethod.GET)
    @ResponseBody
    public DishAllInfoSum getDishAllInfo()
    {
        return orderDishService.getDishAllInfo();
    }

    @RequestMapping(value = "/dishRealPrice",method = RequestMethod.GET)
    @ResponseBody
    public DishRealPrice getDishRealPrice(@RequestParam BigInteger promotion_id,@RequestParam BigInteger dish_id)
    {
        return orderDishService.getDishRealPrice(promotion_id,dish_id);
    }

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    @ResponseBody
    public ResponseOrder submitOrder(@RequestBody OrderSubmit submit)
    {
        return orderDishService.submitOrder(submit);
    }

    @RequestMapping(value = "/tableStatus",method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity setTableStatus(@RequestBody BigInteger table_id)
    {
        if(orderDishService.setTableStatus(table_id))
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/dishComment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addDishComment(@RequestBody CommentDishSubmit submit)
    {
        if(orderDishService.addDishComment(submit))
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/serviceComment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addServiceComment(@RequestBody CommentServiceSubmit submit)
    {
        if(orderDishService.addServiceComment(submit))
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/dishesInOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addOrderDish(@RequestBody AddOrderSubmit submit)
    {
        if(orderDishService.submitAddOrder(submit))
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/credit",method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity setCredit(@RequestBody UpdateUserCredit submit)
    {
        if(orderDishService.updateCredit(submit))
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
