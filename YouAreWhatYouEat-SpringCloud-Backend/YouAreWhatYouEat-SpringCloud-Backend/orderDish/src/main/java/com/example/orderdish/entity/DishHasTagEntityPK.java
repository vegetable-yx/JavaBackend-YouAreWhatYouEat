package com.example.orderdish.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class DishHasTagEntityPK implements Serializable {
    private BigInteger dishId;
    private BigInteger dtagId;

    @Column(name = "DISH_ID", nullable = false, precision = 0)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
    }

    @Column(name = "DTAG_ID", nullable = false, precision = 0)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public BigInteger getDtagId() {
        return dtagId;
    }

    public void setDtagId(BigInteger dtagId) {
        this.dtagId = dtagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishHasTagEntityPK that = (DishHasTagEntityPK) o;
        return Objects.equals(dishId, that.dishId) && Objects.equals(dtagId, that.dtagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, dtagId);
    }
}
