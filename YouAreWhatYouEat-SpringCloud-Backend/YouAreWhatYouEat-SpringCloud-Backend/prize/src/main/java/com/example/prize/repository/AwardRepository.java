package com.example.prize.repository;

import com.example.prize.entity.AwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AwardRepository
    extends JpaRepository<AwardEntity, String>, JpaSpecificationExecutor<AwardEntity>
{
    public List<AwardEntity> findAll();
    public AwardEntity findFirstByLv(String lv);
    public AwardEntity saveAndFlush(AwardEntity awardEntity);
    public Optional<AwardEntity> findByLv(String lv);
    public void deleteByLv(String lv);
}
