package com.my.sensor.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "SENSORLOG", schema = "YANG", catalog = "")
public class SensorLogEntity {
    private BigInteger slogId;
    private BigInteger sensId;
    private Timestamp slogTime;
    private BigDecimal slogValue;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SLOG_ID", nullable = false, precision = 0)
    public BigInteger getSlogId() {
        return slogId;
    }

    public void setSlogId(BigInteger slogId) {
        this.slogId = slogId;
    }

    @Basic
    @Column(name = "SENS_ID", nullable = true, precision = 0)
    public BigInteger getSensId() {
        return sensId;
    }

    public void setSensId(BigInteger sensId) {
        this.sensId = sensId;
    }

    @Basic
    @Column(name = "SLOG_TIME", nullable = false)
    public Timestamp getSlogTime() {
        return slogTime;
    }

    public void setSlogTime(Timestamp slogTime) {
        this.slogTime = slogTime;
    }

    @Basic
    @Column(name = "SLOG_VALUE", nullable = false, precision = 2)
    public BigDecimal getSlogValue() {
        return slogValue;
    }

    public void setSlogValue(BigDecimal slogValue) {
        this.slogValue = slogValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorLogEntity that = (SensorLogEntity) o;
        return Objects.equals(slogId, that.slogId) && Objects.equals(sensId, that.sensId) && Objects.equals(slogTime, that.slogTime) && Objects.equals(slogValue, that.slogValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slogId, sensId, slogTime, slogValue);
    }
}
