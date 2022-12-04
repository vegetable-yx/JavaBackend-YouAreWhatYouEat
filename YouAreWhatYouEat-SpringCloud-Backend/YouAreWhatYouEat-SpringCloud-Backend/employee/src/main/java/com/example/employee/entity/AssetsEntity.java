package com.example.employee.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "ASSETS", schema = "YANG", catalog = "")
public class AssetsEntity {
    private String assetsId;
    private String assetsType;
    private BigInteger employeeId;
    private BigInteger assetsStatus;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ASSETS_ID")
    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    @Basic
    @Column(name = "ASSETS_TYPE")
    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }

    @Basic
    @Column(name = "EMPLOYEE_ID")
    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "ASSETS_STATUS")
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

        if (assetsId != null ? !assetsId.equals(that.assetsId) : that.assetsId != null) return false;
        if (assetsType != null ? !assetsType.equals(that.assetsType) : that.assetsType != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (assetsStatus != null ? !assetsStatus.equals(that.assetsStatus) : that.assetsStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = assetsId != null ? assetsId.hashCode() : 0;
        result = 31 * result + (assetsType != null ? assetsType.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (assetsStatus != null ? assetsStatus.hashCode() : 0);
        return result;
    }
}
