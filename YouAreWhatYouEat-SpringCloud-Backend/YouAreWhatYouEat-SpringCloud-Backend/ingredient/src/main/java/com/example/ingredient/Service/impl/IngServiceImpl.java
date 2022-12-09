package com.example.ingredient.Service.impl;

import com.example.ingredient.Entity.IngredientRecordEntity;
import com.example.ingredient.Entity.IngredientsEntity;
import com.example.ingredient.Repository.IngRecordRepository;
import com.example.ingredient.Repository.IngRepository;
import com.example.ingredient.Repository.SupplierRepository;
import com.example.ingredient.Service.IngService;
import com.example.ingredient.dto.GetIng;
import com.example.ingredient.dto.GetIngItem;
import com.example.ingredient.dto.GetIngRecord;
import com.example.ingredient.dto.GetIngRecordItem;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class IngServiceImpl implements IngService {

    @Resource
    IngRepository ingRepository;

    @Resource
    IngRecordRepository ingRecordRepository;

    @Resource
    SupplierRepository supplierRepository;

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

    @Override
    public GetIngRecord getIngRecord() {
        GetIngRecord result=new GetIngRecord();
        List<GetIngRecordItem> data=new ArrayList<>();
        List<IngredientRecordEntity> ls=ingRecordRepository.findAll();
        if(ls==null){
            return  null;
        }

        for(IngredientRecordEntity x:ls){
            GetIngRecordItem item=new GetIngRecordItem();

            item.setRecordId(x.getRecordId());
            item.setIngName(ingRepository.findFirstByIngrId(x.getIngrId()).getIngrName());
            item.setIngrId(x.getIngrId());
            item.setPrice(x.getPrice());
            item.setDirectorId(x.getDirectorId());
            item.setDirectorName(supplierRepository.findFirstByDirectorId(x.getDirectorId()).getsName());
            item.setMeasureUnit(x.getMeasureUnit());
            item.setProducedDate(x.getProducedDate());
            item.setPurchases(x.getPurchases());
            item.setShelfLife(x.getShelfLife());
            item.setSurplus(x.getSurplus());
            item.setPurchasingDate(x.getPurchasingDate());

            data.add(item);
        }
        result.setTotal(data.size());
        result.setData(data);
        return result;
    }
}
