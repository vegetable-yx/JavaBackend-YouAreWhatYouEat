package com.example.orderlists.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @JsonProperty("order_id")
    String order_id;

    @JsonProperty("creation_time")
    String creation_time;

    @JsonProperty("table_id")
    String tableId;

    @JsonProperty("order_status")
    String orderStatus;

    @JsonProperty("final_payment")
    int finalPay;

    @JsonProperty("discount_price")
    int discountPrice;
}
