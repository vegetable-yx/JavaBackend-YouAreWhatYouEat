package com.example.dishes.Service;

import com.example.dishes.Entity.DishesEntity;
import com.example.dishes.dto.Dish.GetDishItem;
import com.example.dishes.dto.Dish.PostDishItem;
import com.example.dishes.dto.Dish.PutDishItem;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface DishesService {
    public List<GetDishItem> getAllDishes();

    HttpStatus postAddDish(PostDishItem item);

    HttpStatus putUpdateDish(PutDishItem item);
}
