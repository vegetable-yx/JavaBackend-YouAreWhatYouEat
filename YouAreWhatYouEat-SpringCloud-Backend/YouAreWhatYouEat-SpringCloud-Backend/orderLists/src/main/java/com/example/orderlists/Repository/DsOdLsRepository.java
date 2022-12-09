package com.example.orderlists.Repository;

import com.example.orderlists.Entity.DishesEntity;
import com.example.orderlists.Entity.DishorderlistEntity;
import com.example.orderlists.Entity.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface DsOdLsRepository
        extends JpaRepository<DishorderlistEntity, BigInteger>, JpaSpecificationExecutor<DishorderlistEntity> {

    List<DishorderlistEntity> findAllByOrderId(String id);
}
