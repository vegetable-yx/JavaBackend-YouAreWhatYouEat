package com.example.dishes.Controller;

import com.example.dishes.Service.DishesService;
import com.example.dishes.dto.Dish.GetDishItem;
import com.example.dishes.dto.Dish.GetDishItem2;
import com.example.dishes.dto.Dish.PostDishItem;
import com.example.dishes.dto.Dish.PutDishItem;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
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

    @GetMapping("/detailedInfo")
     public ResponseEntity<Object> getAllDishes(){
        List<GetDishItem> ls=dishesService.getAllDishes();
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllDishes2(){
        List<GetDishItem2> ls=dishesService.getAllDishes2();
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<Object> getAllDishesNameById(@RequestParam String dish_id){
        String ls=dishesService.getDishName(dish_id);
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }

    @DeleteMapping ("")
    public ResponseEntity<Object> deleteDish(@RequestParam String id){
        System.out.println(id);
        return  new ResponseEntity<>(dishesService.deleteDish(new BigInteger(id)));
    }

    @PostMapping("")
    public ResponseEntity<Object> postAddDish(@RequestBody PostDishItem p) {
        return new ResponseEntity<>(dishesService.postAddDish(p));
    }
    @PatchMapping  ("")
    public ResponseEntity<Object> putUpdateDish(@RequestBody PutDishItem p) {
        System.out.println(p.toString());
        return new ResponseEntity<>(dishesService.putUpdateDish(p));
    }



}
