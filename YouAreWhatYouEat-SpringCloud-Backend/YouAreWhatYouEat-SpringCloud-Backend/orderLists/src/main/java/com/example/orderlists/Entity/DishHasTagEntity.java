package com.example.orderlists.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "DISH_HAS_TAG", schema = "YANG", catalog = "")
@IdClass(DishHasTagEntityPK.class)
public class DishHasTagEntity {
    private BigInteger dishId;
    private BigInteger dtagId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DISH_ID", nullable = false, precision = 0)
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DTAG_ID", nullable = false, precision = 0)
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
        DishHasTagEntity that = (DishHasTagEntity) o;
        return Objects.equals(dishId, that.dishId) && Objects.equals(dtagId, that.dtagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, dtagId);
    }
}
