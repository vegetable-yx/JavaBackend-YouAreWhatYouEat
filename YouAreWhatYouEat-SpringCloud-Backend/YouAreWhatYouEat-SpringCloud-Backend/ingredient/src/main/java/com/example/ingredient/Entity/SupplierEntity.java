package com.example.ingredient.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "SUPPLIER", schema = "YANG", catalog = "")
public class SupplierEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "S_ID")
    private BigInteger sId;
    @Basic
    @Column(name = "S_NAME")
    private String sName;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "CONTACT")
    private String contact;
    @Basic
    @Column(name = "DIRECTOR_ID")
    private BigInteger directorId;

    public BigInteger getsId() {
        return sId;
    }

    public void setsId(BigInteger sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public BigInteger getDirectorId() {
        return directorId;
    }

    public void setDirectorId(BigInteger directorId) {
        this.directorId = directorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierEntity that = (SupplierEntity) o;
        return Objects.equals(sId, that.sId) && Objects.equals(sName, that.sName) && Objects.equals(address, that.address) && Objects.equals(contact, that.contact) && Objects.equals(directorId, that.directorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sId, sName, address, contact, directorId);
    }
}
