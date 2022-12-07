package com.example.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {
    @JsonProperty("order_id")
    private String order_id;

    @JsonProperty("creation_time")
    private String creation_time;

    @JsonProperty("table_id")
    private String table_id;

    @JsonProperty("order_status")
    private String order_status;

    @JsonProperty("total_price")
    private Double total_price;
}
