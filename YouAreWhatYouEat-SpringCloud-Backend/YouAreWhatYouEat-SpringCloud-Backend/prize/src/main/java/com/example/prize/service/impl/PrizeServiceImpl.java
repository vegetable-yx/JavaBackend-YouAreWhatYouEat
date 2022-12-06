package com.example.prize.service.impl;

import com.example.prize.dto.PrizeOutDto;
import com.example.prize.dto.PrizeRecordDto;
import com.example.prize.entity.AwardEntity;
import com.example.prize.entity.EmployeeEntity;
import com.example.prize.entity.PrizeEntity;
import com.example.prize.repository.AwardRepository;
import com.example.prize.repository.EmployeeRepository;
import com.example.prize.repository.PrizeRepository;
import com.example.prize.service.PrizeService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<PrizeRecordDto> getPrizeRecordInfo(String level, String id, String time_start, String time_end) throws ParseException {
        List<PrizeEntity> prizeEntities = prizeRepository.findAll();
        List<PrizeRecordDto> result = new ArrayList<>();
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

            PrizeRecordDto info = modelMapper.map(prizeEntity, PrizeRecordDto.class);
            info.setName(employeeRepository.findFirstById(prizeEntity.getEmployeeId()).getName());
            info.setAmount(Double.valueOf(awardRepository.findFirstByLv(prizeEntity.getLv()).getAmount()));

            result.add(info);
        }
        return result;
    }

}
