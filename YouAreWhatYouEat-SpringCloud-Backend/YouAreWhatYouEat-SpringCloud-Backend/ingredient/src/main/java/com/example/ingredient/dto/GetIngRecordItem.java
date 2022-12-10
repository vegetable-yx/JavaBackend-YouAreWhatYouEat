package com.example.ingredient.dto;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIngRecordItem {


    BigInteger recordId;
    BigInteger ingrId;

    String ingName;

    Date purchasingDate;
    Integer surplus;
    Integer purchases;
    String measureUnit;
    BigInteger shelfLife;
    Date producedDate;
    Integer price;
    BigInteger directorId;

    String directorName;



}
