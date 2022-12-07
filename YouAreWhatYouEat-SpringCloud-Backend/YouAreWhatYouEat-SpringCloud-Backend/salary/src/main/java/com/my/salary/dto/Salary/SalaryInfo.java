package com.my.salary.dto.Salary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryInfo {
    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("count")
    private BigDecimal count;
}
