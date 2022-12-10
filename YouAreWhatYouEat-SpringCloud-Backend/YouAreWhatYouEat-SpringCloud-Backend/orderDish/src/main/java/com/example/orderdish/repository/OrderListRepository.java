package com.example.orderdish.repository;

import com.example.orderdish.entity.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface OrderListRepository extends JpaRepository<OrderlistEntity, String>,JpaSpecificationExecutor<OrderlistEntity>{
    List<OrderlistEntity> findAllByTableId(BigInteger tableId);
    OrderlistEntity findByOrderId(String orderId);
}
