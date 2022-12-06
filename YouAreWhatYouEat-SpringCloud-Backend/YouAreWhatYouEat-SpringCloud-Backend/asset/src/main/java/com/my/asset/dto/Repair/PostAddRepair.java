package com.my.asset.dto.Repair;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAddRepair {
    @JsonProperty("assetsId")
    private String assetsId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("latitude")
    private String latitude;
}
