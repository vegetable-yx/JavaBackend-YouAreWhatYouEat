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


    BigInteger record_id;
    BigInteger ingr_id;

    String ingr_name;

    Date purchasing_date;
    Integer surplus;
    Integer purchases;
    String measure_unit;
    BigInteger shelf_life;
    Date produced_date;
    Integer price;
    BigInteger director_id;

    String director_name;



}
