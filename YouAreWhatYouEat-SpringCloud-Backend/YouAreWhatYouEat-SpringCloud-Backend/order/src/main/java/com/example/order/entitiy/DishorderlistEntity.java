package com.example.order.entitiy;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "DISHORDERLIST", schema = "YANG", catalog = "")
public class DishorderlistEntity {
    private String dishOrderId;
    private String orderId;
    private BigInteger dishId;
    private Double finalPayment;
    private String dishStatus;
    private String remark;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DISH_ORDER_ID", nullable = false, length = 50)
    public String getDishOrderId() {
        return dishOrderId;
    }

    public void setDishOrderId(String dishOrderId) {
        this.dishOrderId = dishOrderId;
    }

    @Basic
    @Column(name = "ORDER_ID", nullable = false, length = 50)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "DISH_ID", nullable = false, precision = 0)
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
    }

    @Basic
    @Column(name = "FINAL_PAYMENT", nullable = false, precision = 2)
    public Double getFinalPayment() {
        return finalPayment;
    }

    public void setFinalPayment(Double finalPayment) {
        this.finalPayment = finalPayment;
    }

    @Basic
    @Column(name = "DISH_STATUS", nullable = false, length = 20)
    public String getDishStatus() {
        return dishStatus;
    }

    public void setDishStatus(String dishStatus) {
        this.dishStatus = dishStatus;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 150)
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
        return finalPayment == that.finalPayment && Objects.equals(dishOrderId, that.dishOrderId) && Objects.equals(orderId, that.orderId) && Objects.equals(dishId, that.dishId) && Objects.equals(dishStatus, that.dishStatus) && Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishOrderId, orderId, dishId, finalPayment, dishStatus, remark);
    }
}
