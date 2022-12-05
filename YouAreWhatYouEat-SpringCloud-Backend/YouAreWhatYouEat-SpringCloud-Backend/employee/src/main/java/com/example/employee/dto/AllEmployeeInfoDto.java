package com.example.employee.dto;
import com.example.employee.entity.AssetsEntity;
import com.example.employee.entity.PayrollEntity;
import com.example.employee.entity.PrizeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;

@Data
public class AllEmployeeInfoDto {
    private String id;
    private String name;
    private String gender;
    private String occupation;
    private String birthday;
    private Double attendance_rate;
    private Double award_times;
    private String avatar;
}
