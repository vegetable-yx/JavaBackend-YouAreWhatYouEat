package com.example.dishes.Service.impl;

import com.example.dishes.Entity.*;
import com.example.dishes.Repository.*;
import com.example.dishes.Service.DishesService;
import com.example.dishes.dto.Dish.GetDishItem;

import com.example.dishes.dto.Dish.PostDishItem;
import com.example.dishes.dto.Dish.PutDishItem;
import com.example.dishes.dto.List.GetOrderListItem;
import com.example.dishes.dto.List.OrderDishItem;
import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;


@Slf4j
@Service
public class DishesServiceImpl implements DishesService {
    @Value("${myConfiguration.url}")
    private String imgaeUrl;

    @Value("${myConfiguration.path}")
    private String localPath;

    @Resource
    private DishesRepository dishesRepository;

    @Resource
    private DishTagsRepository dishTagsRepository;

    @Resource
    private IngredientsRepository ingredientsRepository;

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private DishHasTagRepository dishHasTagRepository;

    @Resource
    private DishNeedIngrRepository dishNeedIngrRepository;

    @Resource
    private OrderListRepository orderListRepository;

    @Resource
    private DishOrderListRepository dishOrderListRepository;

    @Override
    public List<GetDishItem> getAllDishes() {

    List<DishesEntity> dishesEntities=dishesRepository.findAll();


    List<GetDishItem> result =new ArrayList<>();


    for(DishesEntity dish:dishesEntities){
        GetDishItem info=new GetDishItem();
        info.setRate("");
        info.setDishName(dish.getDishName());
        info.setId(dish.getDishId());
        info.setVideo(dish.getVideo());
        info.setDescription(dish.getDishDescription());
        info.setPrice(dish.getDishPrice());
        info.setPicture(imgaeUrl + "dishes/dish_" + dish.getDishId().toString() + ".png");
        List<DishHasTagEntity> tagLs=new ArrayList<DishHasTagEntity>(dish.getTags());
        List<String> tags=new ArrayList<>();
        //tag
        for(DishHasTagEntity dishHasTagEntity:tagLs){
            Collection<String> tagNames=dishTagsRepository.FindDtagNameById(dishHasTagEntity.getDtagId());

            tags.addAll(tagNames);
        }
        info.setTags(tags);
        //ing
        List<DisheNeedIngrEntity> ingLs=new ArrayList<>(dish.getIngs());
        List<String> ings=new ArrayList<>();
        for(DisheNeedIngrEntity disheNeedIngrEntity:ingLs){
            Collection<String> ingNames=ingredientsRepository.findNameById(disheNeedIngrEntity.getIngrId());

            ings.addAll(ingNames);
        }
        info.setIngs(ings);

        //star
        Collection<BigInteger> stars=commentRepository.FindStarsById(dish.getDishId());
        BigInteger star=BigInteger.valueOf(0);

        if(stars.size()!=0){
            star=star.divide(BigInteger.valueOf(stars.size()));
        }

        info.setRate(star.toString());


        result.add(info);
    }


    return result;
    }

    @Transactional
    @Override
    public HttpStatus postAddDish(PostDishItem item) {

        DishesEntity newDish=new DishesEntity();

        newDish.setDishDescription(item.getDescription());

        newDish.setDishId(item.getId());

        newDish.setDishPrice(item.getPrice());

        newDish.setDishName(item.getDishName());

        newDish.setVideo(item.getVideo());

        try {
            dishesRepository.saveAndFlush(newDish);

            // upload pictures
            Base64.Decoder decoder = Base64.getDecoder();
            if (item.getPicture() != null && !item.getPicture().isEmpty()) {
                // Base64
                byte[] b = decoder.decode(item.getPicture());
                String imgFilePath = localPath + "dishes/dish_" + item.getId().toString() + ".png";
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
            }
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }

        //插tag
        for(String tagName: item.getTags()){
            DishHasTagEntity dishHasTagEntity=new DishHasTagEntity();

            List<BigInteger> id=dishTagsRepository.FindIdByName(tagName);

            dishHasTagEntity.setDishId(item.getId());
            dishHasTagEntity.setDtagId(id.get(0));

            try {
                dishesRepository.flush();
                dishHasTagRepository.saveAndFlush(dishHasTagEntity);
            }
            catch (Exception e) {
                return HttpStatus.BAD_REQUEST;
            }

        }

        //插tag
        for (String ingName:item.getIngs()){
            DisheNeedIngrEntity disheNeedIngrEntity=new DisheNeedIngrEntity();
            disheNeedIngrEntity.setDishId(item.getId());
            disheNeedIngrEntity.setIngrId(ingredientsRepository.findFirstByIngrName(ingName).getIngrId());
            try {
                dishNeedIngrRepository.saveAndFlush(disheNeedIngrEntity);
            }
            catch (Exception e){
                return HttpStatus.BAD_REQUEST;
            }
        }

        return HttpStatus.OK;


    }


    @Transactional
    @Override
    public HttpStatus putUpdateDish(PutDishItem item) {

        try {
            dishHasTagRepository.deleteByDishId(item.getId());
            dishNeedIngrRepository.deleteByDishId(item.getId());
            commentRepository.deleteByDishId(item.getId());
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }


        DishesEntity dishesEntity=new DishesEntity();

        dishesEntity.setDishDescription(item.getDescription());
        dishesEntity.setDishName(item.getDishName());
        dishesEntity.setDishPrice(item.getPrice());
        dishesEntity.setDishId(item.getId());
        dishesEntity.setVideo(dishesRepository.findVideoByDishId(item.getId()).get(0));
        try {
            dishesRepository.saveAndFlush(dishesEntity);
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }
        for(String tagName:item.getTags()){
            DishHasTagEntity dishHasTagEntity=new DishHasTagEntity();

            List<BigInteger> id=dishTagsRepository.FindIdByName(tagName);

            dishHasTagEntity.setDishId(item.getId());
            dishHasTagEntity.setDtagId(id.get(0));

            try {
                dishesRepository.flush();
                dishHasTagRepository.saveAndFlush(dishHasTagEntity);
            }
            catch (Exception e){
                return HttpStatus.BAD_REQUEST;
            }

        }
        //插tag
        for (String ingName:item.getIngs()){
            DisheNeedIngrEntity disheNeedIngrEntity=new DisheNeedIngrEntity();
            disheNeedIngrEntity.setDishId(item.getId());
            disheNeedIngrEntity.setIngrId(ingredientsRepository.findFirstByIngrName(ingName).getIngrId());
            try {
                dishNeedIngrRepository.saveAndFlush(disheNeedIngrEntity);
            }
            catch (Exception e){
                return HttpStatus.BAD_REQUEST;
            }
        }

        return HttpStatus.OK;
    }

    @Transactional
    @Override
    public HttpStatus deleteDish(BigInteger id) {


        if(id==null){
            return HttpStatus.BAD_REQUEST;
        }
        Collection<DishesEntity> info=dishesRepository.findFirstByDishId(id);

        if(info.isEmpty()){
            return HttpStatus.NO_CONTENT;
        }
        try{
            dishHasTagRepository.deleteByDishId(id);
            dishNeedIngrRepository.deleteByDishId(id);
            commentRepository.deleteByDishId(id);
            dishesRepository.deleteByDishId(id);

            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }

    }





}
