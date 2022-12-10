package com.example.orderlists.Entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ORDER_NUMBER", schema = "YANG", catalog = "")
@IdClass(OrderNumberEntityPK.class)
public class OrderNumberEntity {
    private Date orderDate;
    private String orderNumber;
    private String userName;
    private String orderId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_DATE", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_NUMBER", nullable = false, length = 20)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "ORDER_ID", nullable = true, length = 50)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderNumberEntity that = (OrderNumberEntity) o;
        return Objects.equals(orderDate, that.orderDate) && Objects.equals(orderNumber, that.orderNumber) && Objects.equals(userName, that.userName) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDate, orderNumber, userName, orderId);
    }
}
