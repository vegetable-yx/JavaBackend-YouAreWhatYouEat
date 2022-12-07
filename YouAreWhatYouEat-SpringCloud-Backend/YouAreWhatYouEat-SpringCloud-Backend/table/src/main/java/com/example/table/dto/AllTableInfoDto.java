package com.example.table.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class AllTableInfoDto {
    private AllTableSummaryDto summary;
    private AllTableSummary2Dto summary2;
    private List<TableInfoDto> tables=new ArrayList<TableInfoDto>();


    public List<TableInfoDto> getTables() {
        return tables;
    }

    public void setTables(List<TableInfoDto> tables) {
        this.tables = tables;
    }

    public void addTable(TableInfoDto table) {
        this.tables.add(table);
    }




}
