package com.my.asset.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ASSETS")
public class Asset {
    private String assetsId;

    private String assetsType;
    private Integer employeeId;
    private Integer assetsStatus;
}
