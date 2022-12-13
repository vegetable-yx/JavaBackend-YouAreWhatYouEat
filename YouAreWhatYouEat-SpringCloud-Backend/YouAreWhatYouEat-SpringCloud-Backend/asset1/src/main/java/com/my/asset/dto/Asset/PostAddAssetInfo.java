package com.my.asset.dto.Asset;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAddAssetInfo {
    @JsonProperty("employeeId")
    private BigInteger employeeId;

    @JsonProperty("assetsType")
    private String assetsType;

    @JsonProperty("assetsStatus")
    private String assetsStatus;
}
