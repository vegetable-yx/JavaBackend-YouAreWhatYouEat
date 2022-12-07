package com.example.table.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "DINNINGTABLE", schema = "YANG", catalog = "")
public class DinningtableEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TABLE_ID", nullable = false, precision = 0)
    private BigInteger tableId;
    @Basic
    @Column(name = "CUSTOMER_NUMBER", nullable = true, precision = 0)
    private BigInteger customerNumber;
    @Basic
    @Column(name = "TABLE_CAPACITY", nullable = true, precision = 0)
    private BigInteger tableCapacity;
    @Basic
    @Column(name = "OCCUPIED", nullable = true, length = 20)
    private String occupied;

    public BigInteger getTableId() {
        return tableId;
    }

    public void setTableId(BigInteger tableId) {
        this.tableId = tableId;
    }

    public BigInteger getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(BigInteger customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BigInteger getTableCapacity() {
        return tableCapacity;
    }

    public void setTableCapacity(BigInteger tableCapacity) {
        this.tableCapacity = tableCapacity;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DinningtableEntity that = (DinningtableEntity) o;
        return Objects.equals(tableId, that.tableId) && Objects.equals(customerNumber, that.customerNumber) && Objects.equals(tableCapacity, that.tableCapacity) && Objects.equals(occupied, that.occupied);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, customerNumber, tableCapacity, occupied);
    }
}
