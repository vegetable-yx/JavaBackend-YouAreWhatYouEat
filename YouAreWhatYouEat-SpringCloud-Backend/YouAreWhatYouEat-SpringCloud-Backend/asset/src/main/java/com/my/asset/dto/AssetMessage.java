package com.my.asset.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetMessage {
    private Integer total;
    public List<AssetInfo> data;
}
