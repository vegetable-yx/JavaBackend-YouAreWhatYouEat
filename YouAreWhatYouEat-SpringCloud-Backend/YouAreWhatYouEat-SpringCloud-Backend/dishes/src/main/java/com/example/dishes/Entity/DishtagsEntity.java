package com.example.dishes.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "DISHTAGS", schema = "YANG", catalog = "")
public class DishtagsEntity {
    private BigInteger dtagId;
    private String dtagName;


    @Id
    @Column(name = "DTAG_ID", nullable = false, precision = 0)
    public BigInteger getDtagId() {
        return dtagId;
    }

    public void setDtagId(BigInteger dtagId) {
        this.dtagId = dtagId;
    }

    @Basic
    @Column(name = "DTAG_NAME", nullable = false, length = 50)
    public String getDtagName() {
        return dtagName;
    }

    public void setDtagName(String dtagName) {
        this.dtagName = dtagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishtagsEntity that = (DishtagsEntity) o;
        return Objects.equals(dtagId, that.dtagId) && Objects.equals(dtagName, that.dtagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dtagId, dtagName);
    }
}
