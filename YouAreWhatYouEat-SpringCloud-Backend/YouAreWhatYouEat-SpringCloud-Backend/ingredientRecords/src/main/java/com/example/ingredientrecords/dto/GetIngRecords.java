package com.example.ingredientrecords.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.sql.DATE;

import java.math.BigInteger;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIngRecords {

    BigInteger recordId;

    String ingName;

    Date date;

    Integer amount;

    Integer surplus;



}
