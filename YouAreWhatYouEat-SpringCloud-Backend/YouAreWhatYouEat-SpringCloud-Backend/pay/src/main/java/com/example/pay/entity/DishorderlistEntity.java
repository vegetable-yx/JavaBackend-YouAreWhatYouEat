package com.example.pay.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "DISHORDERLIST", schema = "YANG", catalog = "")
public class DishorderlistEntity {
    private String dishOrderId;
    private String orderId;
    private BigInteger dishId;
    private int finalPayment;
    private String dishStatus;
    private String remark;

    @Id
    @Column(name = "DISH_ORDER_ID")
    public String getDishOrderId() {
        return dishOrderId;
    }

    public void setDishOrderId(String dishOrderId) {
        this.dishOrderId = dishOrderId;
    }

    @Basic
    @Column(name = "ORDER_ID")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "DISH_ID")
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
    }

    @Basic
    @Column(name = "FINAL_PAYMENT")
    public int getFinalPayment() {
        return finalPayment;
    }

    public void setFinalPayment(int finalPayment) {
        this.finalPayment = finalPayment;
    }

    @Basic
    @Column(name = "DISH_STATUS")
    public String getDishStatus() {
        return dishStatus;
    }

    public void setDishStatus(String dishStatus) {
        this.dishStatus = dishStatus;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishorderlistEntity that = (DishorderlistEntity) o;

        if (finalPayment != that.finalPayment) return false;
        if (dishOrderId != null ? !dishOrderId.equals(that.dishOrderId) : that.dishOrderId != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (dishId != null ? !dishId.equals(that.dishId) : that.dishId != null) return false;
        if (dishStatus != null ? !dishStatus.equals(that.dishStatus) : that.dishStatus != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dishOrderId != null ? dishOrderId.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (dishId != null ? dishId.hashCode() : 0);
        result = 31 * result + finalPayment;
        result = 31 * result + (dishStatus != null ? dishStatus.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
