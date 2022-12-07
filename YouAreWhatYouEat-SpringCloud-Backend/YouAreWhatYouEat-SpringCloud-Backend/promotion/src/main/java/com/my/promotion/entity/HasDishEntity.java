package com.my.promotion.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "HASDISH", schema = "YANG", catalog = "")
@IdClass(HasDishEntityPK.class)
public class HasDishEntity {
    private BigInteger promotionId;
    private BigInteger dishId;
    private BigDecimal discount;
    private PromotionEntity promotion;
    private DishesEntity dishes;

    @Id
    @Column(name = "PROMOTION_ID", nullable = false, precision = 0)
    public BigInteger getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(BigInteger promotionId) {
        this.promotionId = promotionId;
    }

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
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasDishEntity that = (HasDishEntity) o;
        return Objects.equals(promotionId, that.promotionId) && Objects.equals(dishId, that.dishId) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, dishId, discount);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROMOTION_ID", referencedColumnName = "PROMOTION_ID", nullable = false, insertable = false, updatable = false)
    public PromotionEntity getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionEntity promotionByPromotionId) {
        this.promotion = promotionByPromotionId;
    }

    @ManyToOne
    @JoinColumn(name = "DISH_ID", referencedColumnName = "DISH_ID", nullable = false, insertable = false, updatable = false)
    public DishesEntity getDishes() {
        return dishes;
    }

    public void setDishes(DishesEntity dishes) {
        this.dishes = dishes;
    }
}
