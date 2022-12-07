package com.example.table.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutTableParam {
    @JsonProperty("table_id")
    private BigInteger table_id;

    @JsonProperty("customer_number")
    private BigInteger customer_number;

    @JsonProperty("table_capacity")
    private BigInteger table_capacity;

    @JsonProperty("occupied")
    private String occupied;
}
