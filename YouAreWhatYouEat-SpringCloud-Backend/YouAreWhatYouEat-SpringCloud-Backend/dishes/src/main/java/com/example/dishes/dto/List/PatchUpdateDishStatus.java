package com.example.dishes.dto.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUpdateDishStatus {

    @JsonProperty("dish_order_id")
    String dishOrderId;
    @JsonProperty("dish_status")
    String dishStatus;

}
