package com.my.sensor.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "SENSORS", schema = "YANG", catalog = "")
public class SensorsEntity {
    private BigInteger sensId;
    private String sensType;
    private String sensModel;
    private String sensLocation;
    private Collection<SensorLogEntity> sensorLogs;

    @Id
    @Column(name = "SENS_ID", nullable = false, precision = 0)
    public BigInteger getSensId() {
        return sensId;
    }

    public void setSensId(BigInteger sensId) {
        this.sensId = sensId;
    }

    @Basic
    @Column(name = "SENS_TYPE", nullable = false, length = 50)
    public String getSensType() {
        return sensType;
    }

    public void setSensType(String sensType) {
        this.sensType = sensType;
    }

    @Basic
    @Column(name = "SENS_MODEL", nullable = false, length = 50)
    public String getSensModel() {
        return sensModel;
    }

    public void setSensModel(String sensModel) {
        this.sensModel = sensModel;
    }

    @Basic
    @Column(name = "SENS_LOCATION", nullable = false, length = 50)
    public String getSensLocation() {
        return sensLocation;
    }

    public void setSensLocation(String sensLocation) {
        this.sensLocation = sensLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorsEntity that = (SensorsEntity) o;
        return Objects.equals(sensId, that.sensId) && Objects.equals(sensType, that.sensType) && Objects.equals(sensModel, that.sensModel) && Objects.equals(sensLocation, that.sensLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensId, sensType, sensModel, sensLocation);
    }

    @OneToMany(mappedBy = "sensId")
    public Collection<SensorLogEntity> getSensorLogs() {
        return sensorLogs;
    }

    public void setSensorLogs(Collection<SensorLogEntity> sensorLogs) {
        this.sensorLogs = sensorLogs;
    }
}
