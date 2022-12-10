package com.my.schedule.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "WORK_PLAN", schema = "YANG", catalog = "")
public class WorkPlanEntity {
    private BigInteger id;
    private Timestamp timeStart;
    private Timestamp timeEnd;
    private String place;
    private String occupation;
    private BigInteger no;
    private Collection<AttendEntity> attends;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIME_START", nullable = true)
    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    @Basic
    @Column(name = "TIME_END", nullable = true)
    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Basic
    @Column(name = "PLACE", nullable = true, length = 50)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
    @Column(name = "NO", nullable = true, precision = 0)
    public BigInteger getNo() {
        return no;
    }

    public void setNo(BigInteger no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkPlanEntity that = (WorkPlanEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(timeStart, that.timeStart) && Objects.equals(timeEnd, that.timeEnd) && Objects.equals(place, that.place) && Objects.equals(occupation, that.occupation) && Objects.equals(no, that.no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeStart, timeEnd, place, occupation, no);
    }

    @OneToMany(mappedBy = "planId", cascade = CascadeType.ALL)
    public Collection<AttendEntity> getAttends() {
        return attends;
    }

    public void setAttends(Collection<AttendEntity> attends) {
        this.attends = attends;
    }
}
