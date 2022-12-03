package com.example.table.service.impl;

import com.example.table.entity.DinningtableEntity;
import com.example.table.repository.TableRepository;
import com.example.table.service.TableService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class TableServiceImpl implements TableService {
    @Resource
    private TableRepository tableRepository;

    public DinningtableEntity findByTableId(){
        DinningtableEntity table = tableRepository.findByTableId(BigInteger.valueOf(1));
        System.out.println(table);
        return table;
    }
}
