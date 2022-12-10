package com.example.dishes.Repository;

import com.example.dishes.Entity.CommentOnDishEntity;
import com.example.dishes.Entity.DishesEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Collection;

public interface CommentRepository
        extends JpaRepository<CommentOnDishEntity, BigInteger>, JpaSpecificationExecutor<CommentOnDishEntity> {

    @Query("select p.stars from CommentOnDishEntity p where p.dishId=?1")
    Collection<BigInteger> FindStarsById(BigInteger id);

    @Transactional
    void deleteByDishId(BigInteger id);

}
