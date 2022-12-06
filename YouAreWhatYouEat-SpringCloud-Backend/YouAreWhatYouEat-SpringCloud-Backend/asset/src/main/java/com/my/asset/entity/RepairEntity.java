package com.my.asset.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "REPAIR", schema = "YANG", catalog = "")
@IdClass(RepairEntityPK.class)
public class RepairEntity {
    private String assetsid;
    private String name;
    private String phone;
    private String longitude;
    private String latitude;

    @Id
    @Column(name = "ASSETSID", nullable = false, length = 50)
    public String getAssetsid() {
        return assetsid;
    }

    public void setAssetsid(String assetsid) {
        this.assetsid = assetsid;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Id
    @Column(name = "LONGITUDE", nullable = false, length = 30)
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Id
    @Column(name = "LATITUDE", nullable = false, length = 30)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairEntity that = (RepairEntity) o;
        return Objects.equals(assetsid, that.assetsid) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetsid, name, phone, longitude, latitude);
    }

}
