package com.example.dishes.dto.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class PatchUpdateOrderStatus {

    @JsonProperty("order_id")
    String orderId;
    @JsonProperty("order_status")
    String orderStatus;
}
