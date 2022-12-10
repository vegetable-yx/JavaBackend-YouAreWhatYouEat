package com.example.orderlists.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipOrder {
    String order_id;
    String creation_time;
    BigInteger table_id;
    String order_status;
    BigInteger final_payment;
}
