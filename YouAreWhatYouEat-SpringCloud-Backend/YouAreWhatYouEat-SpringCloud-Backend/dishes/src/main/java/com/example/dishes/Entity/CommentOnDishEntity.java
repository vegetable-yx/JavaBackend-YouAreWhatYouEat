package com.example.dishes.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "COMMENT_ON_DISH", schema = "YANG", catalog = "")
public class CommentOnDishEntity {
    private String commentId;
    private String userName;
    private BigInteger dishId;
    private Timestamp commentTime;
    private BigInteger stars;
    private String commentContent;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "DISH_ID", nullable = false, precision = 0)
    public BigInteger getDishId() {
        return dishId;
    }

    public void setDishId(BigInteger dishId) {
        this.dishId = dishId;
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
        CommentOnDishEntity that = (CommentOnDishEntity) o;
        return Objects.equals(commentId, that.commentId) && Objects.equals(userName, that.userName) && Objects.equals(dishId, that.dishId) && Objects.equals(commentTime, that.commentTime) && Objects.equals(stars, that.stars) && Objects.equals(commentContent, that.commentContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userName, dishId, commentTime, stars, commentContent);
    }
}
