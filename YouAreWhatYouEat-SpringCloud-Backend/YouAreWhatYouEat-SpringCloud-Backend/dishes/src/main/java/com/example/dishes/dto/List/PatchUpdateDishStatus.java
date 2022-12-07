package com.example.dishes.dto.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUpdateDishStatus {

    @JsonProperty("dishOrderId")
    String dishOrderId;
    @JsonProperty("dishStatus")
    String dishStatus;

}
