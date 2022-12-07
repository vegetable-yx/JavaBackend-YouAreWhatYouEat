package com.example.pay.repository;

import com.example.pay.entity.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrderRepository
        extends JpaRepository<OrderlistEntity, String>, JpaSpecificationExecutor<OrderlistEntity>
{
    public Optional<OrderlistEntity> findByOrderId(String id);
}
