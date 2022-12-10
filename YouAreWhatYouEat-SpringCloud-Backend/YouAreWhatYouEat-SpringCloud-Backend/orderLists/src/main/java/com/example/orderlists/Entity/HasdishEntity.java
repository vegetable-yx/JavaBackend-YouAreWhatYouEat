package com.example.orderlists.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "HASDISH", schema = "YANG", catalog = "")
@IdClass(HasdishEntityPK.class)
public class HasdishEntity {
    private BigInteger promotionId;
    private BigInteger dishId;
    private Integer discount;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PROMOTION_ID", nullable = false, precision = 0)
    public BigInteger getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(BigInteger promotionId) {
        this.promotionId = promotionId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DISH_ID", nullable = false, precision = 0)
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
    }

    @Basic
    @Column(name = "DISCOUNT", nullable = true, precision = 2)
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasdishEntity that = (HasdishEntity) o;
        return Objects.equals(promotionId, that.promotionId) && Objects.equals(dishId, that.dishId) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, dishId, discount);
    }
}
