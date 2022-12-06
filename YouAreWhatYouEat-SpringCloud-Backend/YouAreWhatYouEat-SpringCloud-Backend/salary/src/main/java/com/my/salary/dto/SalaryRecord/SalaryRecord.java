package com.my.salary.dto.SalaryRecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryRecord {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("time")
    public String time;
}
