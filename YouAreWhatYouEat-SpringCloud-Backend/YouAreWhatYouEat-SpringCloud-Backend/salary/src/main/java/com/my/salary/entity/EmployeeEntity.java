package com.my.salary.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYEE", schema = "YANG", catalog = "")
public class EmployeeEntity {
    private BigInteger id;
    private String name;
    private String gender;
    private String occupation;
    private Date birthday;
    private Collection<PayrollEntity> payrolls;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "GENDER", nullable = true, length = 6)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "OCCUPATION", nullable = true, length = 50)
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Basic
    @Column(name = "BIRTHDAY", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(occupation, that.occupation) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, occupation, birthday);
    }

    @OneToMany(mappedBy = "employeeId")
    public Collection<PayrollEntity> getPayrolls() {
        return this.payrolls;
    }

    public void setPayrolls(Collection<PayrollEntity> payrolls) {
        this.payrolls = payrolls;
    }
}
