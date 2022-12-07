package com.example.order.repository;

import com.example.order.entitiy.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface OrderListRepository extends JpaRepository<OrderlistEntity, String>,JpaSpecificationExecutor<OrderlistEntity>{
    List<OrderlistEntity> findAllByTableId(BigInteger tableId);
}
