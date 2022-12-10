package com.example.ingredientrecords.Service.impl;

import com.example.ingredientrecords.Entity.IngredientRecordEntity;
import com.example.ingredientrecords.Entity.IngredientsEntity;
import com.example.ingredientrecords.Repository.IngRecordRepository;
import com.example.ingredientrecords.Repository.IngredientRepository;
import com.example.ingredientrecords.Service.IngRecordsService;
import com.example.ingredientrecords.dto.GetIngRecords;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class IngServiceImpl implements IngRecordsService {

    @Resource
    private IngRecordRepository ingRecordRepository;

    @Resource
    private IngredientRepository ingredientsRepository;

    @Override
    public List<GetIngRecords> getIngRecordsList() {

        List<GetIngRecords> getIngRecordsList=new ArrayList<>();

        List<IngredientRecordEntity> ingredientsEntities=ingRecordRepository.findAll();

        for(IngredientRecordEntity ingredientRecordEntity:ingredientsEntities){
            GetIngRecords item=new GetIngRecords();
            item.setRecord_id(ingredientRecordEntity.getRecordId());
            item.setIng_name(ingredientsRepository.findNameById(ingredientRecordEntity.getIngrId()).get(0));
            if(ingredientRecordEntity.getSurplus()!=null)
            {
                item.setSurplus(ingredientRecordEntity.getSurplus());
            }
            if(ingredientRecordEntity.getPurchases()!=null)
            {
                item.setAmount(ingredientRecordEntity.getPurchases());
            }
            if(ingredientRecordEntity.getProducedDate()!=null)
                item.setDate(ingredientRecordEntity.getProducedDate());

            getIngRecordsList.add(item);
        }

        return getIngRecordsList;





    }
}
