package com.my.asset.dto.AssetRecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetRecordInfo {
    @JsonProperty("assets_id")
    private String assetsId;

    @JsonProperty("assets_type")
    private String assetsType;

    @JsonProperty("employee_id")
    private BigInteger employeeId;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("manage_type")
    private String manageType;

    @JsonProperty("manage_date")
    private String manageDate;

    @JsonProperty("manage_reason")
    private String manageReason;

    @JsonProperty("manage_cost")
    private String manageCost;
}
