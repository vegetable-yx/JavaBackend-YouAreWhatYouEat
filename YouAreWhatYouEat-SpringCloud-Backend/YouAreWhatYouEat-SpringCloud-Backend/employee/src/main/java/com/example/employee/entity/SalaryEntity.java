package com.example.employee.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "SALARY", schema = "YANG", catalog = "")
public class SalaryEntity {
    private String occupation;
    private Double amount;

    @Id
    @Column(name = "OCCUPATION")
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Basic
    @Column(name = "AMOUNT")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryEntity that = (SalaryEntity) o;

        if (occupation != null ? !occupation.equals(that.occupation) : that.occupation != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = occupation != null ? occupation.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
