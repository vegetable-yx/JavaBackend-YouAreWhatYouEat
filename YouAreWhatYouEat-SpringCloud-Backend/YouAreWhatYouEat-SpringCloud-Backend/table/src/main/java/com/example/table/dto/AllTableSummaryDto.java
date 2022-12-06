package com.example.table.dto;

import lombok.Data;

@Data
public class AllTableSummaryDto {
    private Integer available_count;
    private Integer occupied_count;
    private Integer total_count;
    private Integer today_customer;
    private Integer total_customer;
}
