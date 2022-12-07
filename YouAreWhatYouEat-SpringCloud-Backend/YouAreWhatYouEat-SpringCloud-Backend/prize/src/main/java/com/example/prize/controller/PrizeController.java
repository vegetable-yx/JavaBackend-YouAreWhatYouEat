package com.example.prize.controller;

import com.example.prize.dto.AwardInDto;
import com.example.prize.dto.PrizeOutDto;
import com.example.prize.dto.PrizeRecordInDto;
import com.example.prize.dto.PrizeRecordOutDto;
import com.example.prize.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<List<PrizeRecordOutDto>> getPrizeRecord(
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String time_start,
            @RequestParam(required = false) String time_end
    )
    {
        List<PrizeRecordOutDto> result = new ArrayList<>();
        try {
            result = prizeService.getPrizeRecordInfo(level, id, time_start, time_end);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "prizes", method = RequestMethod.POST)
    ResponseEntity addPrize(
            @RequestBody AwardInDto awardInDto
    )
    {
        if (prizeService.addAward(awardInDto)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "prizes", method = RequestMethod.PUT)
    ResponseEntity updatePrize(
            @RequestBody AwardInDto awardInDto
    )
    {
        if (prizeService.updateAward(awardInDto)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "prizes", method = RequestMethod.DELETE)
    ResponseEntity deletePrize(
            @RequestParam(required = true) String level
    )
    {
        if (prizeService.deleteAward(level)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "prizeRecord", method = RequestMethod.POST)
    public ResponseEntity addPrizeRecord(
            @RequestBody PrizeRecordInDto prizeRecordInDto
    ) throws ParseException {
        if (prizeService.addPrizeRecord(prizeRecordInDto)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
