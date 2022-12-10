package com.example.ingredientrecords.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "INGREDIENT_RECORD", schema = "YANG", catalog = "")
public class IngredientRecordEntity {
    private BigInteger recordId;
    private BigInteger ingrId;
    private Date purchasingDate;
    private Integer surplus;
    private Integer purchases;
    private String measureUnit;
    private BigInteger shelfLife;
    private Date producedDate;
    private Integer price;
    private BigInteger directorId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RECORD_ID", nullable = false, precision = 0)
    public BigInteger getRecordId() {
        return recordId;
    }

    public void setRecordId(BigInteger recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "INGR_ID", nullable = true, precision = 0)
    public BigInteger getIngrId() {
        return ingrId;
    }

    public void setIngrId(BigInteger ingrId) {
        this.ingrId = ingrId;
    }

    @Basic
    @Column(name = "PURCHASING_DATE", nullable = true)
    public Date getPurchasingDate() {
        return purchasingDate;
    }

    public void setPurchasingDate(Date purchasingDate) {
        this.purchasingDate = purchasingDate;
    }

    @Basic
    @Column(name = "SURPLUS", nullable = true, precision = 2)
    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    @Basic
    @Column(name = "PURCHASES", nullable = true, precision = 2)
    public Integer getPurchases() {
        return purchases;
    }

    public void setPurchases(Integer purchases) {
        this.purchases = purchases;
    }

    @Basic
    @Column(name = "MEASURE_UNIT", nullable = true, length = 10)
    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Basic
    @Column(name = "SHELF_LIFE", nullable = true, precision = 0)
    public BigInteger getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(BigInteger shelfLife) {
        this.shelfLife = shelfLife;
    }

    @Basic
    @Column(name = "PRODUCED_DATE", nullable = true)
    public Date getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(Date producedDate) {
        this.producedDate = producedDate;
    }

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 2)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "DIRECTOR_ID", nullable = true, precision = 0)
    public BigInteger getDirectorId() {
        return directorId;
    }

    public void setDirectorId(BigInteger directorId) {
        this.directorId = directorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientRecordEntity that = (IngredientRecordEntity) o;
        return Objects.equals(recordId, that.recordId) && Objects.equals(ingrId, that.ingrId) && Objects.equals(purchasingDate, that.purchasingDate) && Objects.equals(surplus, that.surplus) && Objects.equals(purchases, that.purchases) && Objects.equals(measureUnit, that.measureUnit) && Objects.equals(shelfLife, that.shelfLife) && Objects.equals(producedDate, that.producedDate) && Objects.equals(price, that.price) && Objects.equals(directorId, that.directorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, ingrId, purchasingDate, surplus, purchases, measureUnit, shelfLife, producedDate, price, directorId);
    }
}
