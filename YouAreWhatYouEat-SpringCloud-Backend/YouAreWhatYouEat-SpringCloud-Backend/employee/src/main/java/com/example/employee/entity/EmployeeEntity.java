package com.example.employee.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "EMPLOYEE", schema = "YANG", catalog = "")
public class EmployeeEntity {
    private BigInteger id;
    private String name;
    private String gender;
    private String occupation;
    private Date birthday;
    private Collection<AssetsEntity> assetsById;
    private Collection<PayrollEntity> parolls;
    private Collection<PrizeEntity> prizes;
    private Collection<AttendEntity> attends;

    @Id
    @Column(name = "ID")
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "GENDER")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "OCCUPATION")
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Basic
    @Column(name = "BIRTHDAY")
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (occupation != null ? !occupation.equals(that.occupation) : that.occupation != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (occupation != null ? occupation.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "employeeId")
    public Collection<AssetsEntity> getAssetsById() {
        return assetsById;
    }

    public void setAssetsById(Collection<AssetsEntity> assetsById) {
        this.assetsById = assetsById;
    }

    @OneToMany(mappedBy = "employeeId")
    public Collection<PayrollEntity> getParolls() {
        return parolls;
    }

    public void setParolls(Collection<PayrollEntity> parolls) { this.parolls = parolls; }

    @OneToMany(mappedBy = "employeeId")
    public Collection<PrizeEntity> getPrizes() { return prizes; }

    public void setPrizes(Collection<PrizeEntity> prizes) { this.prizes = prizes; }

    @OneToMany(mappedBy = "employeeId")
    public Collection<AttendEntity> getAttends() { return attends; }

    public void setAttends(Collection<AttendEntity> attends) { this.attends = attends; }
}
