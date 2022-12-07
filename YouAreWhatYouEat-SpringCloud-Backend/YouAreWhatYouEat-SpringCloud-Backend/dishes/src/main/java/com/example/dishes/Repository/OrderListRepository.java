package com.example.dishes.Repository;

import com.example.dishes.Entity.DishorderlistEntity;
import com.example.dishes.Entity.OrderlistEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

import java.sql.Date;
import java.util.List;

public interface OrderListRepository  extends JpaRepository<OrderlistEntity, BigInteger>, JpaSpecificationExecutor<OrderlistEntity> {

    @Query("SELECT p.orderStatus from OrderlistEntity p where p.orderId=?1")
    List<String> findOrderStatusById(String id);

    @Query("SELECT p.creationTime from OrderlistEntity p where p.orderId=?1")
    List<Date> findCreationTimeById(String id);

    @Query("SELECT p.tableId from OrderlistEntity p where p.orderId=?1")
    List<BigInteger> findTableIdById(String id);

}
