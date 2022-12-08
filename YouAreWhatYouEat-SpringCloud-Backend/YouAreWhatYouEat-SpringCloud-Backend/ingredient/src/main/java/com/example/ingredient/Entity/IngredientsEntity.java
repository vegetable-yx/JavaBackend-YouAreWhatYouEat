package com.example.ingredient.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "INGREDIENTS", schema = "YANG", catalog = "")
public class IngredientsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "INGR_ID")
    private BigInteger ingrId;
    @Basic
    @Column(name = "INGR_NAME")
    private String ingrName;
    @Basic
    @Column(name = "INGR_DESCRIPTION")
    private String ingrDescription;
    @Basic
    @Column(name = "INGR_TYPE")
    private String ingrType;

    public BigInteger getIngrId() {
        return ingrId;
    }

    public void setIngrId(BigInteger ingrId) {
        this.ingrId = ingrId;
    }

    public String getIngrName() {
        return ingrName;
    }

    public void setIngrName(String ingrName) {
        this.ingrName = ingrName;
    }

    public String getIngrDescription() {
        return ingrDescription;
    }

    public void setIngrDescription(String ingrDescription) {
        this.ingrDescription = ingrDescription;
    }

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
