package com.example.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeOrderInfoRequest {
    @JsonProperty("order_id")
    private String order_id;

    @JsonProperty("creation_time")
    private String creation_time;

    @JsonProperty("table_id")
    private BigInteger table_id;

    @JsonProperty("order_status")
    private String order_status;
}
