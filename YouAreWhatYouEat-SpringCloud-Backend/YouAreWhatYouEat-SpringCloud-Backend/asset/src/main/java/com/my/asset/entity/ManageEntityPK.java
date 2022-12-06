package com.my.asset.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ManageEntityPK implements Serializable {
    private String assetsId;
    private String manageType;
    private Date manageDate;
    private String manageReason;
    private String manageCost;

    @Column(name = "ASSETS_ID", nullable = false, length = 50)
    @Id
    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    @Column(name = "MANAGE_TYPE", nullable = false, length = 1000)
    @Id
    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    @Column(name = "MANAGE_DATE", nullable = false)
    @Id
    public Date getManageDate() {
        return manageDate;
    }

    public void setManageDate(Date manageDate) {
        this.manageDate = manageDate;
    }

    @Column(name = "MANAGE_REASON", nullable = false, length = 50)
    @Id
    public String getManageReason() {
        return manageReason;
    }

    public void setManageReason(String manageReason) {
        this.manageReason = manageReason;
    }

    @Column(name = "MANAGE_COST", nullable = false, length = 1000)
    @Id
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
        ManageEntityPK that = (ManageEntityPK) o;
        return Objects.equals(assetsId, that.assetsId) && Objects.equals(manageType, that.manageType) && Objects.equals(manageDate, that.manageDate) && Objects.equals(manageReason, that.manageReason) && Objects.equals(manageCost, that.manageCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetsId, manageType, manageDate, manageReason, manageCost);
    }
}
