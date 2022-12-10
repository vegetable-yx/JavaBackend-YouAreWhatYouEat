package com.example.orderlists.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "PROMOTION", schema = "YANG", catalog = "")
public class PromotionEntity {
    private BigInteger promotionId;
    private Date startTime;
    private Date endTime;
    private String description;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "END_TIME", nullable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
}
