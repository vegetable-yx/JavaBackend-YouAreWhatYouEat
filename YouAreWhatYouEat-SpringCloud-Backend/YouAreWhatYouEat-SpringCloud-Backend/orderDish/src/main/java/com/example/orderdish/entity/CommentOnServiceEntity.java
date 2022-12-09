package com.example.orderdish.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "COMMENT_ON_SERVICE", schema = "YANG", catalog = "")
public class CommentOnServiceEntity {
    private String commentId;
    private String userName;
    private Timestamp commentTime;
    private BigInteger stars;
    private String commentContent;
    private VipEntity vipByUserName;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COMMENT_ID", nullable = false, length = 50)
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "COMMENT_TIME", nullable = true)
    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Basic
    @Column(name = "STARS", nullable = true, precision = 0)
    public BigInteger getStars() {
        return stars;
    }

    public void setStars(BigInteger stars) {
        this.stars = stars;
    }

    @Basic
    @Column(name = "COMMENT_CONTENT", nullable = true, length = 256)
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentOnServiceEntity that = (CommentOnServiceEntity) o;
        return Objects.equals(commentId, that.commentId) && Objects.equals(userName, that.userName) && Objects.equals(commentTime, that.commentTime) && Objects.equals(stars, that.stars) && Objects.equals(commentContent, that.commentContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userName, commentTime, stars, commentContent);
    }

    @ManyToOne
    @JoinColumn(name = "USER_NAME", referencedColumnName = "USER_NAME", nullable = false,insertable = false,updatable = false)
    public VipEntity getVipByUserName() {
        return vipByUserName;
    }

    public void setVipByUserName(VipEntity vipByUserName) {
        this.vipByUserName = vipByUserName;
    }
}
