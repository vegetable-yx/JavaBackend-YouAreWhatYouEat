package com.example.orderdish.repository;

import com.example.orderdish.entity.CommentOnDishEntity;
import com.example.orderdish.entity.CommentOnServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface ServiceCommentRepository extends JpaRepository<CommentOnServiceEntity, String>, JpaSpecificationExecutor<CommentOnServiceEntity> {
    CommentOnServiceEntity findByCommentId(String commentId);
}
