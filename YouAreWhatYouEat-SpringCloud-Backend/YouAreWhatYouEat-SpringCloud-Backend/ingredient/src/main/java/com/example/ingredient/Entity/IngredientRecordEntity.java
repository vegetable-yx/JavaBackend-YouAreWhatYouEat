package com.example.ingredient.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "INGREDIENT_RECORD", schema = "YANG", catalog = "")
public class IngredientRecordEntity {

    @Id
    @Column(name = "RECORD_ID")
    private BigInteger recordId;
    @Basic
    @Column(name = "INGR_ID")
    private BigInteger ingrId;
    @Basic
    @Column(name = "PURCHASING_DATE")
    private Date purchasingDate;
    @Basic
    @Column(name = "SURPLUS")
    private Integer surplus;
    @Basic
    @Column(name = "PURCHASES")
    private Integer purchases;
    @Basic
    @Column(name = "MEASURE_UNIT")
    private String measureUnit;
    @Basic
    @Column(name = "SHELF_LIFE")
    private BigInteger shelfLife;
    @Basic
    @Column(name = "PRODUCED_DATE")
    private Date producedDate;
    @Basic
    @Column(name = "PRICE")
    private Integer price;
    @Basic
    @Column(name = "DIRECTOR_ID")
    private BigInteger directorId;

    public BigInteger getRecordId() {
        return recordId;
    }

    public void setRecordId(BigInteger recordId) {
        this.recordId = recordId;
    }

    public BigInteger getIngrId() {
        return ingrId;
    }

    public void setIngrId(BigInteger ingrId) {
        this.ingrId = ingrId;
    }

    public Date getPurchasingDate() {
        return purchasingDate;
    }

    public void setPurchasingDate(Date purchasingDate) {
        this.purchasingDate = purchasingDate;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public Integer getPurchases() {
        return purchases;
    }

    public void setPurchases(Integer purchases) {
        this.purchases = purchases;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public BigInteger getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(BigInteger shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Date getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(Date producedDate) {
        this.producedDate = producedDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

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
