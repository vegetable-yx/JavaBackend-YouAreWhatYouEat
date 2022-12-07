package com.my.salary.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "SALARY", schema = "YANG", catalog = "")
public class SalaryEntity {
    private String occupation;
    private BigDecimal amount;
    private Collection<EmployeeEntity> employees;

    @Id
    @Column(name = "OCCUPATION", nullable = false, length = 50)
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = true, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryEntity that = (SalaryEntity) o;
        return Objects.equals(occupation, that.occupation) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupation, amount);
    }

    @OneToMany(mappedBy = "occupation")
    public Collection<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<EmployeeEntity> employees) {
        this.employees = employees;
    }
}
