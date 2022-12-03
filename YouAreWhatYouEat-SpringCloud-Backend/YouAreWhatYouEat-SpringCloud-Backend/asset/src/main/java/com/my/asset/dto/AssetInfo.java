package com.my.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetInfo {
    private String assets_id;
    private String assets_types;
    private Integer employee_id;
    private String employee_name;
    private String assets_status;
    private List<RepairInfo> repair;
}
