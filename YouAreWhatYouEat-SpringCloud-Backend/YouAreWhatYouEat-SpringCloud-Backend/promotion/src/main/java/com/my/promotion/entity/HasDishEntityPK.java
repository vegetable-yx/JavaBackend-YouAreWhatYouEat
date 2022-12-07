package com.my.promotion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class HasDishEntityPK implements Serializable {
    private BigInteger promotionId;
    private BigInteger dishId;

    @Column(name = "PROMOTION_ID", nullable = false, precision = 0)
    @Id
    public BigInteger getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(BigInteger promotionId) {
        this.promotionId = promotionId;
    }

    @Column(name = "DISH_ID", nullable = false, precision = 0)
    @Id
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasDishEntityPK that = (HasDishEntityPK) o;
        return Objects.equals(promotionId, that.promotionId) && Objects.equals(dishId, that.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, dishId);
    }
}
