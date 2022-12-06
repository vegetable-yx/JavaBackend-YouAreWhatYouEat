package com.my.salary.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PAYROLL", schema = "YANG", catalog = "")
@IdClass(PayrollEntityPK.class)
public class PayrollEntity {
    private Timestamp payDatetime;
    private BigInteger employeeId;

    @Id
    @Column(name = "PAY_DATETIME", nullable = false)
    public Timestamp getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Timestamp payDatetime) {
        this.payDatetime = payDatetime;
    }

    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false, precision = 0)
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
        PayrollEntity that = (PayrollEntity) o;
        return Objects.equals(payDatetime, that.payDatetime) && Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payDatetime, employeeId);
    }

}
