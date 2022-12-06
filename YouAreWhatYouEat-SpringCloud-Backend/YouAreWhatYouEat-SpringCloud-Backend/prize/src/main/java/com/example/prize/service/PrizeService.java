package com.example.prize.service;

import com.example.prize.dto.PrizeOutDto;
import com.example.prize.dto.PrizeRecordDto;

import java.text.ParseException;
import java.util.List;

public interface PrizeService {
    public List<PrizeOutDto> getPrizesInfo();
    public List<PrizeRecordDto> getPrizeRecordInfo(String level, String id, String time_start, String time_end) throws ParseException;
}
