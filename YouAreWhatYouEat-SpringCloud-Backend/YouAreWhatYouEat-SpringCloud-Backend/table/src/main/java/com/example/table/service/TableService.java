package com.example.table.service;

import com.example.table.dto.*;
import com.example.table.entity.DinningtableEntity;

import java.math.BigInteger;

public interface TableService {
    public DinningtableEntity findByTableId(BigInteger id);

    QueueTableRespond getQueueTable(BigInteger req);

    public AllTableInfoDto findAllTables();
    public boolean setTableStatus(PutTableParam info);
}
