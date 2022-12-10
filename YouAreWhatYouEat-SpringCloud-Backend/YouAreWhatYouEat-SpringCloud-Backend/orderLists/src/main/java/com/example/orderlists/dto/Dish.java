package com.example.orderlists.dto;


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
    String dishOrderId;
    String dishName;
    String orderId;
    Date creationTime;
    BigInteger dishId;
    String dishStatus;
    BigInteger pay;
}