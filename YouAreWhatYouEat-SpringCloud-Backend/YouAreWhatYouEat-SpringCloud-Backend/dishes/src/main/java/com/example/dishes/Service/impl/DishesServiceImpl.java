package com.example.dishes.Service.impl;

import com.example.dishes.Entity.DishHasTagEntity;
import com.example.dishes.Entity.DisheNeedIngrEntity;
import com.example.dishes.Entity.DishesEntity;
import com.example.dishes.Entity.DishorderlistEntity;
import com.example.dishes.Repository.*;
import com.example.dishes.Service.DishesService;
import com.example.dishes.dto.Dish.GetDishItem;

import com.example.dishes.dto.Dish.PostDishItem;
import com.example.dishes.dto.Dish.PutDishItem;
import com.example.dishes.dto.List.GetOrderListItem;
import com.example.dishes.dto.List.OrderDishItem;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Slf4j
@Service
public class DishesServiceImpl implements DishesService {



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
        for(BigInteger s:stars){
            star=star.add(s);
        }
        star=star.divide(BigInteger.valueOf(stars.size()));
        info.setRate(star.toString());


        result.add(info);
    }


    return result;
    }


    @Override
    public HttpStatus postAddDish(PostDishItem item) {

        System.out.println(item.toString());
        DishesEntity newDish=new DishesEntity();

        newDish.setDishDescription(item.getDescription());

        newDish.setDishId(item.getId());

        newDish.setDishPrice(item.getPrice());

        newDish.setDishName(item.getDishName());

        newDish.setVideo(item.getVideo());


        System.out.println(newDish.getDishPrice());
        System.out.println(newDish.getDishId());
        System.out.println(newDish.getDishName());
        System.out.printf(newDish.getVideo());
        System.out.println(newDish.getDishDescription());
        System.out.println(newDish);
        try {
            System.out.println("Dish CHENGGONG");

            dishesRepository.saveAndFlush(newDish);


        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }
        for(String ingName:item.getIngs()){
            DishHasTagEntity dishHasTagEntity=new DishHasTagEntity();
            List<BigInteger> id=ingredientsRepository.findIdByName(ingName);
            dishHasTagEntity.setDishId(item.getId());
            dishHasTagEntity.setDtagId(id.get(0));
            try {
                dishHasTagRepository.saveAndFlush(dishHasTagEntity);
            }
            catch (Exception e){
                return HttpStatus.BAD_REQUEST;
            }

        }
        for (String tagName:item.getTags()){
            DisheNeedIngrEntity disheNeedIngrEntity=new DisheNeedIngrEntity();
            disheNeedIngrEntity.setDishId(item.getId());
            disheNeedIngrEntity.setIngrId(dishTagsRepository.FindIdByName(tagName).get(0));
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
        return null;
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
            System.out.println("开删除"+id);
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
