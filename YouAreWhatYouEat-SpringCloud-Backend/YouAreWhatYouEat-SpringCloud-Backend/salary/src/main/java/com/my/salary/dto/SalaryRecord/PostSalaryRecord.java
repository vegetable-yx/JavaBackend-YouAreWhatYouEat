package com.my.salary.dto.SalaryRecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSalaryRecord {
    @JsonProperty("id")
    private String id;

    @JsonProperty("time")
    private String time;
}
