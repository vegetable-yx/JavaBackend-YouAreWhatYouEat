package com.my.asset.dto.AssetRecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPutAssetRecordInfo {
    @JsonProperty("assets_id")
    String assetsId;

    @JsonProperty("employee_id")
    BigInteger employeeId;

    @JsonProperty("manage_type")
    String manageType;

    @JsonProperty("manage_date")
    String manageDate;

    @JsonProperty("manage_reason")
    String manageReason;

    @JsonProperty("manage_cost")
    String manageCost;
}
