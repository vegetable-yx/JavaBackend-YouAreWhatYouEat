package com.example.dishes.Service.impl;

import com.example.dishes.Entity.DishesEntity;
import com.example.dishes.Repository.DishesRepository;
import com.example.dishes.Service.DishesService;
import com.example.dishes.dto.Dish.GetDishItem;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class DishesServiceImpl implements DishesService {



    @Resource
    private DishesRepository dishesRepository;


    @Override
    public List<GetDishItem> getAllDishes() {

    List<DishesEntity> dishesEntities=dishesRepository.findAll();

    List<GetDishItem> result =new ArrayList<>();

    ModelMapper modelMapper = new ModelMapper();
    for(DishesEntity dish:dishesEntities){
        GetDishItem info=new GetDishItem();
        info.setRate("");
        info.setDishName(dish.getDishName());
        info.setId(dish.getDishId());
        info.setVideo(dish.getVideo());
        info.setDescription(dish.getDishDescription());
        info.setPrice(dish.getDishPrice());

        result.add(info);
    }



    return result;
    }
}
