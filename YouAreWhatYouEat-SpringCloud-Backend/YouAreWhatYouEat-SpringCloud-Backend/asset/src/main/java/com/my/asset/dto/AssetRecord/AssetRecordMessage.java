package com.my.asset.dto.AssetRecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetRecordMessage {
    @JsonProperty("data")
    private List<AssetRecordInfo> data = new ArrayList<>();

    @JsonProperty("total")
    private Integer total;
}
