package com.my.asset.dto.Asset;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetMessage {
    @JsonProperty("total")
    private Integer total;

    @JsonProperty("data")
    private List<AssetInfo> data = new ArrayList<>();
}
