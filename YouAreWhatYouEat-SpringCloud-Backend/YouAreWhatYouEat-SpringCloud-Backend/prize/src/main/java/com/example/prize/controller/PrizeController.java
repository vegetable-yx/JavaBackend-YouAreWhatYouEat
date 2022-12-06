package com.example.prize.controller;

import com.example.prize.dto.PrizeOutDto;
import com.example.prize.dto.PrizeRecordDto;
import com.example.prize.service.PrizeService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class PrizeController {
    @Autowired
    private PrizeService prizeService;

    @RequestMapping(value = "prizes", method = RequestMethod.GET)
    public ResponseEntity<List<PrizeOutDto>> getPrizes() {
        List<PrizeOutDto> result = new ArrayList<>();
        try {
            result = prizeService.getPrizesInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "prizeRecord", method = RequestMethod.GET)
    public ResponseEntity<List<PrizeRecordDto>> getPrizeRecord(
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String time_start,
            @RequestParam(required = false) String time_end
    )
    {
        List<PrizeRecordDto> result = new ArrayList<>();
        try {
            result = prizeService.getPrizeRecordInfo(level, id, time_start, time_end);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
