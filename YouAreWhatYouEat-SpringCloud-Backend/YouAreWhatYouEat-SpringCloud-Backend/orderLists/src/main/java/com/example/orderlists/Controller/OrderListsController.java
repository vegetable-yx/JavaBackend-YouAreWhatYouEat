package com.example.orderlists.Controller;


import com.example.orderlists.Entity.OrderlistEntity;
import com.example.orderlists.Service.impl.OdLsServiceImpl;
import com.example.orderlists.dto.GetDishNums;
import com.example.orderlists.dto.GetDishes;
import com.example.orderlists.dto.GetOrders;
import com.example.orderlists.dto.GetVipOrders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/orderlists")
@CrossOrigin
public class OrderListsController {

    private final OdLsServiceImpl odLsService;


    public OrderListsController(OdLsServiceImpl odLsService) {
        this.odLsService = odLsService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllOrders(@RequestParam String begin,@RequestParam String end){
        GetOrders ls=odLsService.getOders(begin, end);
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }

    @GetMapping("/dishOrders")
    public ResponseEntity<Object> getAllDishes(@RequestParam String begin,@RequestParam String end){
        GetDishes ls=odLsService.getDishes(begin, end);
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }

    @GetMapping("/vipOrders")
    public ResponseEntity<Object> getVipOrders(String begin,String end){
        GetVipOrders ls=  odLsService.getVipOders(begin,end);
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }

    @GetMapping("/dishOrderNum")
    public ResponseEntity<Object> getAllDishesNum(@RequestParam String begin,@RequestParam String end){
        GetDishNums ls=odLsService.getDishesNum(begin, end);
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }
}
