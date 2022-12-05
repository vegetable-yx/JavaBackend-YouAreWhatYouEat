package com.example.table.service;

import com.example.table.entity.DinningtableEntity;

import java.math.BigInteger;

public interface TableService {
    public DinningtableEntity findByTableId(BigInteger id);
}
