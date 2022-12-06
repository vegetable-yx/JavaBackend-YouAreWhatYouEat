package com.example.table.controller;

import com.example.table.dto.AllTableInfoDto;
import com.example.table.entity.DinningtableEntity;
import com.example.table.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Controller
public class TableController {
    private TableService tableService;

    public TableController(TableService tableService)
    {
        this.tableService=tableService;
    }

    @RequestMapping("/test")
    @ResponseBody
    public DinningtableEntity findByTableId()
    {
        DinningtableEntity table=this.tableService.findByTableId(BigInteger.valueOf(1));
        return table;
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public ResponseEntity<AllTableInfoDto> getAllTables(
            //@RequestParam(value = "token", required = false) String token
    )
    {
        AllTableInfoDto result = tableService.findAllTables();
        if (result==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity<AllTableInfoDto>(result, HttpStatus.OK);
    }

}
