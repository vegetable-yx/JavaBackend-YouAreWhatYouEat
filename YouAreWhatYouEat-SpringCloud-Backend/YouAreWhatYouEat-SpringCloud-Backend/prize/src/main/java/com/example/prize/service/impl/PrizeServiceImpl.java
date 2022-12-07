package com.example.prize.service.impl;

import com.example.prize.dto.AwardInDto;
import com.example.prize.dto.PrizeOutDto;
import com.example.prize.dto.PrizeRecordInDto;
import com.example.prize.dto.PrizeRecordOutDto;
import com.example.prize.entity.AwardEntity;
import com.example.prize.entity.PrizeEntity;
import com.example.prize.repository.AwardRepository;
import com.example.prize.repository.EmployeeRepository;
import com.example.prize.repository.PrizeRepository;
import com.example.prize.service.PrizeService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrizeServiceImpl implements PrizeService {
    @Resource
    private AwardRepository awardRepository;

    @Resource
    private PrizeRepository prizeRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    public List<PrizeOutDto> getPrizesInfo() {
        List<AwardEntity> awardEntities = awardRepository.findAll();
        List<PrizeOutDto> result = new ArrayList<>();

        for(AwardEntity awardEntity : awardEntities) {
            PrizeOutDto info = new PrizeOutDto();
            info.setLevel(awardEntity.getLv());
            info.setAmount(Double.valueOf(awardEntity.getAmount()));
            info.setSummary(Double.valueOf(awardEntity.getPrizes().size()));

            result.add(info);
        }
        return result;
    }

    @Override
    public List<PrizeRecordOutDto> getPrizeRecordInfo(String level, String id, String time_start, String time_end) throws ParseException {
        List<PrizeEntity> prizeEntities = prizeRepository.findAll();
        List<PrizeRecordOutDto> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (PrizeEntity prizeEntity : prizeEntities) {
            if (!level.isEmpty() && !level.equals(prizeEntity.getLv())) continue;
            if (!id.isEmpty() && !id.equals(prizeEntity.getEmployeeId().toString())) continue;

            Date start, end;
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            if (time_start.isEmpty()) start = ft.parse("1970-1-1 0:0:0");
            else start = ft.parse(time_start);
            if (time_end.isEmpty()) end = ft.parse("2999-12-12 23:59:59");
            else end = ft.parse(time_end);

            System.out.println(ft.format(start));
            System.out.println(ft.format(end));

            Date current = prizeEntity.getPrizeDatetime();
            if (current.before(start) || current.after(end)) continue;

            PrizeRecordOutDto info = modelMapper.map(prizeEntity, PrizeRecordOutDto.class);
            info.setName(employeeRepository.findFirstById(prizeEntity.getEmployeeId()).getName());
            info.setAmount(Double.valueOf(awardRepository.findFirstByLv(prizeEntity.getLv()).getAmount()));

            result.add(info);
        }
        return result;
    }

    @Override
    public boolean addAward(AwardInDto awardInDto) {
        if (awardRepository.findByLv(awardInDto.getLv()).isPresent())
            return false;
        ModelMapper modelMapper = new ModelMapper();
        AwardEntity awardEntity = modelMapper.map(awardInDto, AwardEntity.class);
        awardEntity.setPrizes(null);

        try {
            awardRepository.saveAndFlush(awardEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAward(AwardInDto awardInDto) {
        if (awardRepository.findByLv(awardInDto.getLv()).isEmpty())
            return false;
        ModelMapper modelMapper = new ModelMapper();
        AwardEntity awardEntity = modelMapper.map(awardInDto, AwardEntity.class);
        awardEntity.setPrizes(null);

        try {
            awardRepository.saveAndFlush(awardEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteAward(String level) {
        Optional<AwardEntity> awardEntity = awardRepository.findByLv(level);
        if (awardEntity.isEmpty())
            return false;

        String lv = awardEntity.get().getLv();
        try {
            awardRepository.deleteByLv(level);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addPrizeRecord(PrizeRecordInDto prizeRecordInDto) throws ParseException {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setLv(prizeRecordInDto.getLv());
        prizeEntity.setEmployeeId(BigInteger.valueOf(Long.valueOf(prizeRecordInDto.getEmployeeId())));
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date d = ft.parse(prizeRecordInDto.getPrizeDatetime());
        prizeEntity.setPrizeDatetime(new java.sql.Date(d.getTime()));

        if (prizeRepository.findByLvAndEmployeeIdAndPrizeDatetime(prizeEntity.getLv(), prizeEntity.getEmployeeId(), prizeEntity.getPrizeDatetime()).isPresent())
            return false;

        System.out.println(prizeEntity.getLv());
        System.out.println(prizeEntity.getPrizeDatetime());
        System.out.println(prizeEntity.getEmployeeId());
        try {
            prizeRepository.saveAndFlush(prizeEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(213312312);
            return false;
        }
    }
}
