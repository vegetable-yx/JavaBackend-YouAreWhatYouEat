package com.my.asset.dto.Asset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.my.asset.dto.Repair.RepairInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetInfo {
    @JsonProperty("assets_id")
    private String assetsId;

    @JsonProperty("assets_type")
    private String assetsType;

    @JsonProperty("employee_id")
    private BigInteger employeeId;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("assets_status")
    private String assetsStatus;

    @JsonProperty("repair")
    private List<RepairInfo> repair = new ArrayList<>();
}
