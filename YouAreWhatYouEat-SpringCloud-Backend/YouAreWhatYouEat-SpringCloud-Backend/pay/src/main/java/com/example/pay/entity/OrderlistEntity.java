package com.example.pay.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Collection;

@Entity
@Table(name = "ORDERLIST", schema = "YANG", catalog = "")
public class OrderlistEntity {
    private String orderId;
    private Date creationTime;
    private BigInteger tableId;
    private String orderStatus;
    private Collection<DishorderlistEntity> dishorderlistsByOrderId;

    @Id
    @Column(name = "ORDER_ID")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "CREATION_TIME")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Basic
    @Column(name = "TABLE_ID")
    public BigInteger getTableId() {
        return tableId;
    }

    public void setTableId(BigInteger tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "ORDER_STATUS")
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

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (creationTime != null ? !creationTime.equals(that.creationTime) : that.creationTime != null) return false;
        if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;
        if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (tableId != null ? tableId.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "orderId")
    public Collection<DishorderlistEntity> getDishorderlistsByOrderId() {
        return dishorderlistsByOrderId;
    }

    public void setDishorderlistsByOrderId(Collection<DishorderlistEntity> dishorderlistsByOrderId) {
        this.dishorderlistsByOrderId = dishorderlistsByOrderId;
    }
}
