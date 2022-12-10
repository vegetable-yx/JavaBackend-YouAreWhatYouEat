package com.example.orderlists.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.sql.DATE;

import java.math.BigInteger;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @JsonProperty("dish_order_id")
    String dishOrderId;
    @JsonProperty("dish_name")
    String dishName;
    @JsonProperty("order_id")
    String orderId;
    @JsonProperty("order_creation_time")
    Date creationTime;
    @JsonProperty("dish_id")
    BigInteger dishId;
    @JsonProperty("dish_status")
    String dishStatus;
    @JsonProperty("final_payment")
    BigInteger pay;
}