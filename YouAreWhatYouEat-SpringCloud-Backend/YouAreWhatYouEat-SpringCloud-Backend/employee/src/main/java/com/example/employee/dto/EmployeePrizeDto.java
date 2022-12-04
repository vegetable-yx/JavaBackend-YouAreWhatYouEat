package com.example.employee.dto;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class EmployeePrizeDto {

    @JsonProperty("time")
    public Date prizeDatetime;

    @JsonProperty("level")
    public String lv;

    public Date getPrizeDatetime() {
        return prizeDatetime;
    }

    public void setPrizeDatetime(Date prizeDatetime) {
        this.prizeDatetime = prizeDatetime;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }
}
