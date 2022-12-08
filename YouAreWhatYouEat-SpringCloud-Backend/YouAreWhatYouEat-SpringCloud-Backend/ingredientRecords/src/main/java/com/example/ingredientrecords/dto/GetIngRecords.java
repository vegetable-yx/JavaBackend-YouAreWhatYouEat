package com.example.ingredientrecords.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.sql.DATE;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIngRecords {

    BigInteger recordId;

    String ingName;

    DATE date;

    BigInteger amount;

    BigInteger surplus;



}
