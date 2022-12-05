package com.example.employee.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "PAYROLL", schema = "YANG", catalog = "")
@IdClass(PayrollEntityPK.class)
public class PayrollEntity {
    private Date payDatetime;
    private BigInteger employeeId;

    @Id
    @Column(name = "PAY_DATETIME")
    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    @Id
    @Column(name = "EMPLOYEE_ID")
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
