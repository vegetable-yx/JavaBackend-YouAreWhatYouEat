package com.example.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

public class PayrollEntityPK implements Serializable {
    private Date payDatetime;
    private BigInteger employeeId;

    @Column(name = "PAY_DATETIME")
    @Id
    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    @Column(name = "EMPLOYEE_ID")
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

        if (payDatetime != null ? !payDatetime.equals(that.payDatetime) : that.payDatetime != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = payDatetime != null ? payDatetime.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        return result;
    }
}
