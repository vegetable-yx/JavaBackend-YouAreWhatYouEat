package com.example.dishes.Service;

import com.example.dishes.Entity.DishesEntity;
import com.example.dishes.dto.Dish.GetDishItem;
import com.example.dishes.dto.Dish.GetDishItem2;
import com.example.dishes.dto.Dish.PostDishItem;
import com.example.dishes.dto.Dish.PutDishItem;
import com.example.dishes.dto.List.GetOrderListItem;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.util.List;

public interface DishesService {
    public List<GetDishItem> getAllDishes();


    public List<GetDishItem2> getAllDishes2();
    HttpStatus postAddDish(PostDishItem item);

    HttpStatus putUpdateDish(PutDishItem item);

    HttpStatus deleteDish(BigInteger id);

    String getDishName(String id);



}
