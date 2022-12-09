package com.example.orderlists.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    String orderId;

    String creationTime;

    String tableId;

    String orderStatus;

    int finalPay;

    int discountPrice;
}
