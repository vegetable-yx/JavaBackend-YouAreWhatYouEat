package com.example.ingredient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostIngRrd {

    @JsonProperty("record_id")
    BigInteger recordId;

    @JsonProperty("ingr_id")
    BigInteger ingrId;

    @JsonProperty("purchasing_date")
    String purchasingDate;

    @JsonProperty("measure_unit")
    String measureUnit;

    @JsonProperty("shelf_life")
    BigInteger shelfLife;



    @JsonProperty("produced_date")
    String producedDate;

    @JsonProperty("price")
    BigInteger price;

    @JsonProperty("purchases")
    String purchases;

    @JsonProperty("surplus")
    String surplus;








    @JsonProperty("director_id")
    BigInteger directorId;


}
