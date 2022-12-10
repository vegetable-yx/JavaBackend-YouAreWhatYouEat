package com.example.ingredient.Repository;

import com.example.ingredient.Entity.IngredientsEntity;
import com.example.ingredient.Entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface SupplierRepository
        extends JpaRepository<SupplierEntity, BigInteger>, JpaSpecificationExecutor<SupplierEntity> {

    public SupplierEntity findFirstByDirectorId(BigInteger id);
}
