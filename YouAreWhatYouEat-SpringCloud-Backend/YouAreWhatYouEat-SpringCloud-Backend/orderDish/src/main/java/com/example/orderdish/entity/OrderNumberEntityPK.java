package com.example.orderdish.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class OrderNumberEntityPK implements Serializable {
    private Date orderDate;
    private String orderNumber;

    @Column(name = "ORDER_DATE", nullable = false)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "ORDER_NUMBER", nullable = false, length = 20)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderNumberEntityPK that = (OrderNumberEntityPK) o;
        return Objects.equals(orderDate, that.orderDate) && Objects.equals(orderNumber, that.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDate, orderNumber);
    }
}
