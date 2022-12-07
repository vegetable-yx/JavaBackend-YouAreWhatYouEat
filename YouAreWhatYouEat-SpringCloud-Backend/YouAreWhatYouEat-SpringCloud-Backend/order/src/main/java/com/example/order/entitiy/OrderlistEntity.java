package com.example.order.entitiy;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ORDERLIST", schema = "YANG", catalog = "")
public class OrderlistEntity {
    private String orderId;
    private Date creationTime;
    private BigInteger tableId;
    private String orderStatus;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID", nullable = false, length = 50)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "CREATION_TIME", nullable = false)
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Basic
    @Column(name = "TABLE_ID", nullable = false, precision = 0)
    public BigInteger getTableId() {
        return tableId;
    }

    public void setTableId(BigInteger tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "ORDER_STATUS", nullable = false, length = 20)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderlistEntity that = (OrderlistEntity) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(creationTime, that.creationTime) && Objects.equals(tableId, that.tableId) && Objects.equals(orderStatus, that.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, creationTime, tableId, orderStatus);
    }
}
