package com.example.employee.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "AWARD", schema = "YANG", catalog = "")
public class AwardEntity {
    private String lv;
    private Integer amount;
    private Collection<PrizeEntity> prizes;

    @Id
    @Column(name = "LV")
    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    @Basic
    @Column(name = "AMOUNT")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwardEntity that = (AwardEntity) o;

        if (lv != null ? !lv.equals(that.lv) : that.lv != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lv != null ? lv.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "employeeId")
    public Collection<PrizeEntity> getPrizes() { return prizes; }

    public void setPrizes(Collection<PrizeEntity> prizes) { this.prizes = prizes; }
}
