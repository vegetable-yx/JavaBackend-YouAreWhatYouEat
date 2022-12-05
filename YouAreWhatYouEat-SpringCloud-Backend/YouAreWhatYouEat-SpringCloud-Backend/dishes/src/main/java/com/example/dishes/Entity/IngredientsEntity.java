package com.example.dishes.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "INGREDIENTS", schema = "YANG", catalog = "")
public class IngredientsEntity {
    private BigInteger ingrId;
    private String ingrName;
    private String ingrDescription;
    private String ingrType;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "INGR_ID", nullable = false, precision = 0)
    public BigInteger getIngrId() {
        return ingrId;
    }

    public void setIngrId(BigInteger ingrId) {
        this.ingrId = ingrId;
    }

    @Basic
    @Column(name = "INGR_NAME", nullable = false, length = 50)
    public String getIngrName() {
        return ingrName;
    }

    public void setIngrName(String ingrName) {
        this.ingrName = ingrName;
    }

    @Basic
    @Column(name = "INGR_DESCRIPTION", nullable = false, length = 1000)
    public String getIngrDescription() {
        return ingrDescription;
    }

    public void setIngrDescription(String ingrDescription) {
        this.ingrDescription = ingrDescription;
    }

    @Basic
    @Column(name = "INGR_TYPE", nullable = true, length = 50)
    public String getIngrType() {
        return ingrType;
    }

    public void setIngrType(String ingrType) {
        this.ingrType = ingrType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientsEntity that = (IngredientsEntity) o;
        return Objects.equals(ingrId, that.ingrId) && Objects.equals(ingrName, that.ingrName) && Objects.equals(ingrDescription, that.ingrDescription) && Objects.equals(ingrType, that.ingrType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingrId, ingrName, ingrDescription, ingrType);
    }
}
