package com.my.schedule.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "ATTEND", schema = "YANG", catalog = "")
@IdClass(AttendEntityPK.class)
public class AttendEntity {
    private BigInteger planId;
    private BigInteger employeeId;
    private Boolean attendance;

    @Id
    @Column(name = "PLAN_ID", nullable = false, precision = 0)
    public BigInteger getPlanId() {
        return planId;
    }

    public void setPlanId(BigInteger planId) {
        this.planId = planId;
    }

    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false, precision = 0)
    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "ATTENDANCE", nullable = true, precision = 0)
    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendEntity that = (AttendEntity) o;
        return Objects.equals(planId, that.planId) && Objects.equals(employeeId, that.employeeId) && Objects.equals(attendance, that.attendance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, employeeId, attendance);
    }

}
