package com.example.table.dto;

import lombok.Data;

@Data
public class TableInfoDto {
    private Integer table_id;

    public Integer getTable_id() {
        return table_id;
    }

    public void setTable_id(Integer table_id) {
        this.table_id = table_id;
    }

    public Integer getCustomer_number() {
        if(customer_number==null)
        {
            return 0;
        }
        return customer_number;
    }

    public void setCustomer_number(Integer customer_number) {
        this.customer_number = customer_number;
    }

    public Integer getTable_capacity() {
        if(table_capacity==null)
        {
            return 0;
        }
        return table_capacity;
    }

    public void setTable_capacity(Integer table_capacity) {
        this.table_capacity = table_capacity;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    private Integer customer_number;
    private Integer table_capacity;
    private String occupied;
}
