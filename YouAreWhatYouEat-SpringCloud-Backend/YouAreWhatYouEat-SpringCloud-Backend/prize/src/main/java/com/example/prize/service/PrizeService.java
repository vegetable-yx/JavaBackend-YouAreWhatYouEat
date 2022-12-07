package com.example.prize.service;

import com.example.prize.dto.AwardInDto;
import com.example.prize.dto.PrizeOutDto;
import com.example.prize.dto.PrizeRecordInDto;
import com.example.prize.dto.PrizeRecordOutDto;

import java.text.ParseException;
import java.util.List;

public interface PrizeService {
    public List<PrizeOutDto> getPrizesInfo();
    public List<PrizeRecordOutDto> getPrizeRecordInfo(String level, String id, String time_start, String time_end) throws ParseException;
    public boolean addAward(AwardInDto awardInDto);
    public boolean updateAward(AwardInDto awardInDto);
    public boolean deleteAward(String level);
    public boolean addPrizeRecord(PrizeRecordInDto prizeRecordInDto) throws ParseException;
}
