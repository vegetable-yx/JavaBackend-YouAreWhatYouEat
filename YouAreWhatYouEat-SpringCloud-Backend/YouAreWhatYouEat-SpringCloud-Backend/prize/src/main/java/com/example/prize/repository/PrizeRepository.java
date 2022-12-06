package com.example.prize.repository;

import com.example.prize.entity.PrizeEntity;
import com.example.prize.entity.PrizeEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PrizeRepository
        extends JpaRepository<PrizeEntity, PrizeEntityPK>, JpaSpecificationExecutor<PrizeEntity>
{
    public List<PrizeEntity> findAll();
}
