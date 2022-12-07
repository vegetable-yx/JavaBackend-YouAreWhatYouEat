package com.my.salary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class PayrollEntityPK implements Serializable {
    private Timestamp payDatetime;
    private BigInteger employeeId;

    @Column(name = "PAY_DATETIME", nullable = false)
    @Id
    public Timestamp getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Timestamp payDatetime) {
        this.payDatetime = payDatetime;
    }

    @Column(name = "EMPLOYEE_ID", nullable = false, precision = 0)
    @Id
    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayrollEntityPK that = (PayrollEntityPK) o;
        return Objects.equals(payDatetime, that.payDatetime) && Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payDatetime, employeeId);
    }
}
