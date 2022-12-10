package com.example.dishes.Repository;

import com.example.dishes.Entity.DishorderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface DishOrderListRepository extends JpaRepository<DishorderlistEntity, BigInteger>, JpaSpecificationExecutor<DishorderlistEntity> {

    @Query("SELECT p.dishStatus FROM DishorderlistEntity p WHERE p.orderId=?1")
    List<String> findDishStatusById(String id);

    @Query("select distinct orderId,dishOrderId FROM  DishorderlistEntity group by orderId")
    List<String> findAllId();

    @Query("SELECT p.dishId FROM DishorderlistEntity p where p.dishOrderId=?1")
    List<BigInteger> findDishIdByDishOrderId(String id);

    @Query("SELECT p.orderId FROM DishorderlistEntity p where p.dishOrderId=?1")
    List<String> findOrderIdByDishOrderId(String id);

    @Query("SELECT p.finalPayment FROM DishorderlistEntity p where p.dishOrderId=?1")
    List<BigInteger> findPayIdByDishOrderId(String id);

    @Query("SELECT p.remark FROM DishorderlistEntity p where p.dishOrderId=?1")
    List<String> findRemarkIdByDishOrderId(String id);

}
