package com.example.ingredient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostIng {

    @JsonProperty("ingr_id")
    BigInteger ingId;
    @JsonProperty("ingr_name")
    String ingName;
    @JsonProperty("ingr_type")
    String ingType;
    @JsonProperty("ingr_description")
    String ingDescription;
}
