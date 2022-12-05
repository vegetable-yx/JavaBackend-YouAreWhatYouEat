package com.example.dishes.Controller;

import com.example.dishes.Service.DishesService;
import com.example.dishes.dto.Dish.GetDishItem;
import jakarta.annotation.Resource;
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
@RequestMapping(value = "/api/v1/dishes")
public class DishesController {

    private final DishesService dishesService;

    @Autowired
    public DishesController(DishesService dishesService) {
        this.dishesService = dishesService;
    }

    @GetMapping("")
     public ResponseEntity<Object> getAllDishes(){
        List<GetDishItem> ls=dishesService.getAllDishes();
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }



}
