package com.my.promotion.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "DISHES", schema = "YANG", catalog = "")
public class DishesEntity {
    private BigInteger dishId;
    private String dishName;
    private BigDecimal dishPrice;
    private String dishDescription;
    private String video;
    private Collection<HasDishEntity> correspondingPromotionTags;

    @Id
    @Column(name = "DISH_ID", nullable = false, precision = 0)
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
    }

    @Basic
    @Column(name = "DISH_NAME", nullable = false, length = 50)
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Basic
    @Column(name = "DISH_PRICE", nullable = false, precision = 2)
    public BigDecimal getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(BigDecimal dishPrice) {
        this.dishPrice = dishPrice;
    }

    @Basic
    @Column(name = "DISH_DESCRIPTION", nullable = false, length = 1000)
    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    @Basic
    @Column(name = "VIDEO", nullable = true, length = 50)
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishesEntity that = (DishesEntity) o;
        return Objects.equals(dishId, that.dishId) && Objects.equals(dishName, that.dishName) && Objects.equals(dishPrice, that.dishPrice) && Objects.equals(dishDescription, that.dishDescription) && Objects.equals(video, that.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, dishName, dishPrice, dishDescription, video);
    }

    @OneToMany(mappedBy = "dishId", cascade = CascadeType.ALL)
    public Collection<HasDishEntity> getCorrespondingPromotionTags() {
        return this.correspondingPromotionTags;
    }

    public void setCorrespondingPromotionTags(Collection<HasDishEntity> correspondingPromotionTags) {
        this.correspondingPromotionTags = correspondingPromotionTags;
    }
}
