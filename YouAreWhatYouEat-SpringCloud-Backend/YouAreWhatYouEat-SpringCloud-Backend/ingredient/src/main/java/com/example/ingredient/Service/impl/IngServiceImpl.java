package com.example.ingredient.Service.impl;

import com.example.ingredient.Entity.IngredientRecordEntity;
import com.example.ingredient.Entity.IngredientsEntity;
import com.example.ingredient.Repository.IngRecordRepository;
import com.example.ingredient.Repository.IngRepository;
import com.example.ingredient.Repository.SupplierRepository;
import com.example.ingredient.Service.IngService;
import com.example.ingredient.dto.*;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.math.BigInteger;
import java.sql.Date;
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

    @Override
    public HttpStatus deleteIng(BigInteger id) {
        IngredientsEntity info=ingRepository.findFirstByIngrId(id);
        if(info==null){
            return HttpStatus.NO_CONTENT;
        }
        try{
            System.out.println("开删除"+id);
            ingRepository.deleteById(id);

            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }


    }

    @Override
    public HttpStatus deleteIngRecord(BigInteger id) {

        IngredientRecordEntity info=ingRecordRepository.findFirstByRecordId(id);
        if(info==null){
            return HttpStatus.NO_CONTENT;
        }
        try{
            System.out.println("开删除"+id);
            ingRecordRepository.deleteById(id);

            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public HttpStatus addIng(PostIng ing) {
        IngredientsEntity newIng=new IngredientsEntity();
        newIng.setIngrId(ing.getIngId());
        newIng.setIngrDescription(ing.getIngDescription());
        newIng.setIngrName(ing.getIngName());
        newIng.setIngrType(ing.getIngType());
        System.out.println(newIng);
        try {
            ingRepository.save(newIng);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public HttpStatus addIngRecord(PostIngRrd record) {
        IngredientRecordEntity newReord=new IngredientRecordEntity();

        newReord.setDirectorId(record.getDirectorId());
        newReord.setRecordId(record.getRecordId());
        newReord.setIngrId(record.getIngrId());

        newReord.setPrice(Integer.valueOf(record.getPrice().toString()));
        newReord.setPurchases(Integer.valueOf(record.getPurchases()));
        newReord.setMeasureUnit(record.getMeasureUnit());

        newReord.setSurplus(Integer.valueOf(record.getSurplus().toString()));
        newReord.setProducedDate(Date.valueOf(record.getProducedDate()));
        newReord.setPurchasingDate(Date.valueOf(record.getPurchasingDate()));

        newReord.setShelfLife(record.getShelfLife());

        try {
            ingRecordRepository.save(newReord);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }


    }

    @Override
    public HttpStatus updateIng(PostIng ing) {
        IngredientsEntity ingredientsEntity= ingRepository.findFirstByIngrId(ing.getIngId());
        if(ingredientsEntity==null){
            return HttpStatus.BAD_REQUEST;
        }
        ingredientsEntity.setIngrType(ing.getIngType()) ;
        ingredientsEntity.setIngrName(ing.getIngName());
        ingredientsEntity.setIngrDescription(ing.getIngDescription());
        try {
            ingRepository.saveAndFlush(ingredientsEntity);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }

    }

    @Override
    public HttpStatus updateIngRecord(PostIngRrd record) {

        IngredientRecordEntity newReord=ingRecordRepository.findFirstByRecordId(record.getRecordId());
        System.out.println(11111);
        if(newReord==null){
            System.out.println("没有");
            return HttpStatus.BAD_REQUEST;
        }
        System.out.printf("有的");
        newReord.setDirectorId(record.getDirectorId());
        newReord.setRecordId(record.getRecordId());
        newReord.setIngrId(record.getIngrId());

        newReord.setPrice(Integer.valueOf(record.getPrice().toString()));
        newReord.setPurchases(Integer.valueOf(record.getPurchases()));
        newReord.setMeasureUnit(record.getMeasureUnit());

        newReord.setSurplus(Integer.valueOf(record.getSurplus().toString()));
        newReord.setProducedDate(Date.valueOf(record.getProducedDate()));
        newReord.setPurchasingDate(Date.valueOf(record.getPurchasingDate()));

        newReord.setShelfLife(record.getShelfLife());


        try {
            System.out.println("kai chaaaaa");
            ingRecordRepository.saveAndFlush(newReord);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }


    }
}
