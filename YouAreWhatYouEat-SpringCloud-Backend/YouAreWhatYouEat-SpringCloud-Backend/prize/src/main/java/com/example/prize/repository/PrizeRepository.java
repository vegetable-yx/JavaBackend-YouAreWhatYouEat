package com.example.prize.repository;

import com.example.prize.entity.PrizeEntity;
import com.example.prize.entity.PrizeEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrizeRepository
        extends JpaRepository<PrizeEntity, PrizeEntityPK>, JpaSpecificationExecutor<PrizeEntity>
{
    public List<PrizeEntity> findAll();
    public Optional<PrizeEntity> findByLvAndEmployeeIdAndPrizeDatetime(String lv, BigInteger id, Timestamp time);
    public PrizeEntity saveAndFlush(PrizeEntity prizeEntity);
}
