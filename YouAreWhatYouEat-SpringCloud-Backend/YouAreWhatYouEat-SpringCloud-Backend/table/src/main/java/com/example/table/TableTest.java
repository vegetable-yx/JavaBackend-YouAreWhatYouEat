package com.example.table;

import com.example.table.entity.DinningtableEntity;
import com.example.table.repository.TableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest
public class TableTest {
    @Autowired
    private TableRepository tableRepository;

    @Test
    public void testFindByTableId()
    {
        DinningtableEntity table=tableRepository.findByTableId(BigInteger.valueOf(1));
        System.out.println(table);
    }
}
