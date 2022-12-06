package com.my.asset.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "MANAGE", schema = "YANG", catalog = "")
@IdClass(ManageEntityPK.class)
public class ManageEntity {
    private BigInteger employeeId;
    private String assetsId;
    private String manageType;
    private Date manageDate;
    private String manageReason;
    private String manageCost;
    private EmployeeEntity employeeByEmployeeId;
    private AssetsEntity assetsByAssetsId;

    @Basic
    @Column(name = "EMPLOYEE_ID", nullable = false, precision = 0)
    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "ASSETS_ID", nullable = false, length = 50)
    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    @Id
    @Column(name = "MANAGE_TYPE", nullable = false, length = 1000)
    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    @Id
    @Column(name = "MANAGE_DATE", nullable = false)
    public Date getManageDate() {
        return manageDate;
    }

    public void setManageDate(Date manageDate) {
        this.manageDate = manageDate;
    }

    @Id
    @Column(name = "MANAGE_REASON", nullable = false, length = 50)
    public String getManageReason() {
        return manageReason;
    }

    public void setManageReason(String manageReason) {
        this.manageReason = manageReason;
    }

    @Id
    @Column(name = "MANAGE_COST", nullable = false, length = 1000)
    public String getManageCost() {
        return manageCost;
    }

    public void setManageCost(String manageCost) {
        this.manageCost = manageCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManageEntity that = (ManageEntity) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(assetsId, that.assetsId) && Objects.equals(manageType, that.manageType) && Objects.equals(manageDate, that.manageDate) && Objects.equals(manageReason, that.manageReason) && Objects.equals(manageCost, that.manageCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, assetsId, manageType, manageDate, manageReason, manageCost);
    }

    @Override
    public String toString() {
        return "ManageEntity{" +
                "employeeId=" + employeeId +
                ", assetsId='" + assetsId + '\'' +
                ", manageType='" + manageType + '\'' +
                ", manageDate=" + manageDate +
                ", manageReason='" + manageReason + '\'' +
                ", manageCost='" + manageCost + '\'' +
                ", employeeByEmployeeId=" + employeeByEmployeeId +
                ", assetsByAssetsId=" + assetsByAssetsId +
                '}';
    }
}
