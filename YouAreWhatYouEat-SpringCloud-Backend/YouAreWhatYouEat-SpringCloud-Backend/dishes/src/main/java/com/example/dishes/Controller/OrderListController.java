package com.example.dishes.Controller;


import com.example.dishes.Service.DishesService;
import com.example.dishes.Service.impl.OrderListService;
import com.example.dishes.dto.Dish.GetDishItem;
import com.example.dishes.dto.List.GetOrderListItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/dishesInOrder")
public class OrderListController {

    private final OrderListService orderListService;

    @Autowired
    public OrderListController(OrderListService orderListService) {
        this.orderListService = orderListService;
    }

    @GetMapping("")
    public ResponseEntity<Object> GetAllDishOrder(){
        List<GetOrderListItem> ls=orderListService.getOrderList();
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }
}
