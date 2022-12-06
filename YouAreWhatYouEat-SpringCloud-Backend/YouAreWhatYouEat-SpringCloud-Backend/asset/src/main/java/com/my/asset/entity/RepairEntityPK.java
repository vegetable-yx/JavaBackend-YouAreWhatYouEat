package com.my.asset.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class RepairEntityPK implements Serializable {
    private String assetsid;
    private String longitude;
    private String latitude;

    @Column(name = "ASSETSID", nullable = false, length = 50)
    @Id
    public String getAssetsid() {
        return assetsid;
    }

    public void setAssetsid(String assetsid) {
        this.assetsid = assetsid;
    }

    @Column(name = "LONGITUDE", nullable = false, length = 30)
    @Id
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(name = "LATITUDE", nullable = false, length = 30)
    @Id
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
        RepairEntityPK that = (RepairEntityPK) o;
        return Objects.equals(assetsid, that.assetsid) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetsid, longitude, latitude);
    }
}
