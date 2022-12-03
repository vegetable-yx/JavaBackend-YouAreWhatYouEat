package com.my.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairInfo {
    private String name;
    private String phone;
    private String longitude;
    private String latitude;
}
