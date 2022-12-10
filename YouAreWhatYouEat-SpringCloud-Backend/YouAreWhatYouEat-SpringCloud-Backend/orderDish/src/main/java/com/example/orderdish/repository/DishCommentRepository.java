package com.example.orderdish.repository;

import com.example.orderdish.entity.CommentOnDishEntity;
import com.example.orderdish.entity.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface DishCommentRepository extends JpaRepository<CommentOnDishEntity, String>, JpaSpecificationExecutor<CommentOnDishEntity> {
    List<CommentOnDishEntity> findAllByDishId(BigInteger dishId);
    CommentOnDishEntity findByCommentId(String commentId);
}
