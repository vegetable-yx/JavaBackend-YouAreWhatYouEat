package com.example.dishes.Service;

import com.example.dishes.Entity.DishesEntity;
import com.example.dishes.dto.Dish.GetDishItem;

import java.util.List;

public interface DishesService {
    public List<GetDishItem> getAllDishes();
}
