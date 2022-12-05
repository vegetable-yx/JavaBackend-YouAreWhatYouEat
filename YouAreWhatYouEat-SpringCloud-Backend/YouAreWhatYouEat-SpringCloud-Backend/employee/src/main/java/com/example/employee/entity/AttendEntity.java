package com.example.employee.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "ATTEND", schema = "YANG", catalog = "")
@IdClass(AttendEntityPK.class)
public class AttendEntity {
    private BigInteger planId;
    private BigInteger employeeId;
    private BigInteger attendance;

    @Id
    @Column(name = "PLAN_ID")
    public BigInteger getPlanId() {
        return planId;
    }

    public void setPlanId(BigInteger planId) {
        this.planId = planId;
    }

    @Id
    @Column(name = "EMPLOYEE_ID")
    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "ATTENDANCE")
    public BigInteger getAttendance() {
        return attendance;
    }

    public void setAttendance(BigInteger attendance) {
        this.attendance = attendance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendEntity that = (AttendEntity) o;

        if (planId != null ? !planId.equals(that.planId) : that.planId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (attendance != null ? !attendance.equals(that.attendance) : that.attendance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = planId != null ? planId.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (attendance != null ? attendance.hashCode() : 0);
        return result;
    }
}
