package com.my.promotion.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PROMOTION", schema = "YANG", catalog = "")
public class PromotionEntity {
    private BigInteger promotionId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String description;

    private Collection<HasDishEntity> correspondingDishTags;

    @Id
    @Column(name = "PROMOTION_ID", nullable = false, precision = 0)
    public BigInteger getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(BigInteger promotionId) {
        this.promotionId = promotionId;
    }

    @Basic
    @Column(name = "START_TIME", nullable = true)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "END_TIME", nullable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 256)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionEntity that = (PromotionEntity) o;
        return Objects.equals(promotionId, that.promotionId) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, startTime, endTime, description);
    }

    @OneToMany(mappedBy = "promotionId", cascade = CascadeType.ALL)
    public Collection<HasDishEntity> getCorrespondingDishTags() {
        return this.correspondingDishTags;
    }

    public void setCorrespondingDishTags(Collection<HasDishEntity> correspondingDishTags) {
        this.correspondingDishTags = correspondingDishTags;
    }
}
