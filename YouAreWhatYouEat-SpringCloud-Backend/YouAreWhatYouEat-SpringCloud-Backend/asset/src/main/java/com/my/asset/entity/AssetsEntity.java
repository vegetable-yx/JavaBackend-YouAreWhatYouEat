package com.my.asset.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "ASSETS", schema = "YANG", catalog = "")
public class AssetsEntity {
    private String assetsId;
    private String assetsType;
    private BigInteger employeeId;
    private BigInteger assetsStatus;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ASSETS_ID", nullable = false, length = 50)
    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    @Basic
    @Column(name = "ASSETS_TYPE", nullable = false, length = 50)
    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }

    @Basic
    @Column(name = "EMPLOYEE_ID", nullable = false, precision = 0)
    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "ASSETS_STATUS", nullable = false, precision = 0)
    public BigInteger getAssetsStatus() {
        return assetsStatus;
    }

    public void setAssetsStatus(BigInteger assetsStatus) {
        this.assetsStatus = assetsStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetsEntity that = (AssetsEntity) o;
        return Objects.equals(assetsId, that.assetsId) && Objects.equals(assetsType, that.assetsType) && Objects.equals(employeeId, that.employeeId) && Objects.equals(assetsStatus, that.assetsStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetsId, assetsType, employeeId, assetsStatus);
    }
}
