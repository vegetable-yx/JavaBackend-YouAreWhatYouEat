package com.example.ingredient.Service.impl;

import com.example.ingredient.Entity.IngredientsEntity;
import com.example.ingredient.Repository.IngRepository;
import com.example.ingredient.Service.IngService;
import com.example.ingredient.dto.GetIng;
import com.example.ingredient.dto.GetIngItem;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class IngServiceImpl implements IngService {

    @Resource
    IngRepository ingRepository;

    @Override
    public GetIng getIngMessage(String name) {

        IngredientsEntity ingredientsEntity=ingRepository.findAllByIngrName(name);
        if(ingredientsEntity==null){
            return null;
        }

        GetIng result=new GetIng();
        result.setTotal(1);

        GetIngItem item=new GetIngItem();
        item.setIngDescription(ingredientsEntity.getIngrDescription());
        item.setIngId(ingredientsEntity.getIngrId());
        item.setIngType(ingredientsEntity.getIngrType());
        item.setIngName(ingredientsEntity.getIngrName());

        List<GetIngItem> getIngItems=new ArrayList<>();
        getIngItems.add(item);
        result.setData(getIngItems);
        System.out.println(ingredientsEntity);
        return result;
    }

    @Override
    public GetIng getIngMessage() {

        List<IngredientsEntity> ls=ingRepository.findAll();
        if(ls==null){
            return null;
        }
        GetIng result=new GetIng();
        result.setTotal(ls.size());
        List<GetIngItem> getIngItems=new ArrayList<>();
        for(IngredientsEntity ingredientsEntity:ls){

            GetIngItem item=new GetIngItem();
            item.setIngDescription(ingredientsEntity.getIngrDescription());
            item.setIngId(ingredientsEntity.getIngrId());
            item.setIngType(ingredientsEntity.getIngrType());
            item.setIngName(ingredientsEntity.getIngrName());

            getIngItems.add(item);
            result.setData(getIngItems);


        }

        return result;
    }
}
