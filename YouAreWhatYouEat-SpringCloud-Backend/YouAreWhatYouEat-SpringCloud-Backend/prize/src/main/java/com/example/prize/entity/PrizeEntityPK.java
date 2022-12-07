package com.example.prize.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

public class PrizeEntityPK implements Serializable {
    private Date prizeDatetime;
    private BigInteger employeeId;
    private String lv;

    @Column(name = "PRIZE_DATETIME")
    @Id
    public Date getPrizeDatetime() {
        return prizeDatetime;
    }

    public void setPrizeDatetime(Date prizeDatetime) {
        this.prizeDatetime = prizeDatetime;
    }

    @Column(name = "EMPLOYEE_ID")
    @Id
    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "LV")
    @Id
    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrizeEntityPK that = (PrizeEntityPK) o;

        if (prizeDatetime != null ? !prizeDatetime.equals(that.prizeDatetime) : that.prizeDatetime != null)
            return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (lv != null ? !lv.equals(that.lv) : that.lv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prizeDatetime != null ? prizeDatetime.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (lv != null ? lv.hashCode() : 0);
        return result;
    }
}
