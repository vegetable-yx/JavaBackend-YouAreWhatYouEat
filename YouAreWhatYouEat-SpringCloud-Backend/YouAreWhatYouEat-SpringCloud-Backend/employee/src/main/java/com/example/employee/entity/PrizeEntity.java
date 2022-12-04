package com.example.employee.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "PRIZE", schema = "YANG", catalog = "")
@IdClass(PrizeEntityPK.class)
public class PrizeEntity {
    private Date prizeDatetime;
    private BigInteger employeeId;
    private String lv;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PRIZE_DATETIME")
    public Date getPrizeDatetime() {
        return prizeDatetime;
    }

    public void setPrizeDatetime(Date prizeDatetime) {
        this.prizeDatetime = prizeDatetime;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EMPLOYEE_ID")
    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LV")
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

        PrizeEntity that = (PrizeEntity) o;

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
