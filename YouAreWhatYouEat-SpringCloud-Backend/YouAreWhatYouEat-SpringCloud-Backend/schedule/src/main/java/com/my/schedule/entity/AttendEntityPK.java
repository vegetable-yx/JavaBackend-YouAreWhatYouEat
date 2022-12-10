package com.my.schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class AttendEntityPK implements Serializable {
    private BigInteger planId;
    private BigInteger employeeId;

    @Column(name = "PLAN_ID", nullable = false, precision = 0)
    @Id
    public BigInteger getPlanId() {
        return planId;
    }

    public void setPlanId(BigInteger planId) {
        this.planId = planId;
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
        AttendEntityPK that = (AttendEntityPK) o;
        return Objects.equals(planId, that.planId) && Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, employeeId);
    }
}
