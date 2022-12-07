package com.example.table.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueTableRespond {
    private boolean has_table;
    private String table_id;
    private String queue_id;
}
