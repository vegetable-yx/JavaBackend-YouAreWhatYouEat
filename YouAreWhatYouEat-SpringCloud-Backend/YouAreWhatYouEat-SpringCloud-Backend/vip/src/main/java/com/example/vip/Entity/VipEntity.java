package com.example.vip.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "VIP", schema = "YANG", catalog = "")
public class VipEntity {
    private String userName;
    private String password;
    private Date birthday;
    private String gender;
    private int balance;
    private BigInteger credit;
    private String isDefault;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_NAME", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "BIRTHDAY", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "GENDER", nullable = true, length = 20)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "BALANCE", nullable = false, precision = 2)
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "CREDIT", nullable = false, precision = 0)
    public BigInteger getCredit() {
        return credit;
    }

    public void setCredit(BigInteger credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "IS_DEFAULT", nullable = false, length = 20)
    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VipEntity vipEntity = (VipEntity) o;
        return balance == vipEntity.balance && Objects.equals(userName, vipEntity.userName) && Objects.equals(password, vipEntity.password) && Objects.equals(birthday, vipEntity.birthday) && Objects.equals(gender, vipEntity.gender) && Objects.equals(credit, vipEntity.credit) && Objects.equals(isDefault, vipEntity.isDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, birthday, gender, balance, credit, isDefault);
    }
}
