package com.example.dishes.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "DISHE_NEED_INGR", schema = "YANG", catalog = "")
@IdClass(DisheNeedIngrEntityPK.class)
public class DisheNeedIngrEntity {
    private BigInteger dishId;
    private BigInteger ingrId;

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
    @Column(name = "INGR_ID", nullable = false, precision = 0)
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
        DisheNeedIngrEntity that = (DisheNeedIngrEntity) o;
        return Objects.equals(dishId, that.dishId) && Objects.equals(ingrId, that.ingrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, ingrId);
    }
}
