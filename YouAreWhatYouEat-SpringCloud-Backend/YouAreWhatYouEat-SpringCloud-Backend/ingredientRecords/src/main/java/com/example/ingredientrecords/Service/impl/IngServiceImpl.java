package com.example.ingredientrecords.Service.impl;

import com.example.ingredientrecords.Service.IngRecordsService;
import com.example.ingredientrecords.dto.GetIngRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class IngServiceImpl implements IngRecordsService {

    @Override
    public List<GetIngRecords> getIngRecordsList() {
        return null;
    }
}
