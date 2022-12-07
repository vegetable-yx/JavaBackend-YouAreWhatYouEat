package com.my.salary.dto.Salary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSalaryInfo {
    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("amount")
    private String amount;
}
