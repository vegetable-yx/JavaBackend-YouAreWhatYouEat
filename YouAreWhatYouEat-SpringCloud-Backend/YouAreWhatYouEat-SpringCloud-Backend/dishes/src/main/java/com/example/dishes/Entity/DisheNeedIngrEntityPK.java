package com.example.dishes.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class DisheNeedIngrEntityPK implements Serializable {
    private BigInteger dishId;
    private BigInteger ingrId;


    @Id
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
    }

    @Column(name = "INGR_ID", nullable = false, precision = 0)
    @Id
    public BigInteger getIngrId() {
        return ingrId;
    }

    public void setIngrId(BigInteger ingrId) {
        this.ingrId = ingrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisheNeedIngrEntityPK that = (DisheNeedIngrEntityPK) o;
        return Objects.equals(dishId, that.dishId) && Objects.equals(ingrId, that.ingrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, ingrId);
    }
}
