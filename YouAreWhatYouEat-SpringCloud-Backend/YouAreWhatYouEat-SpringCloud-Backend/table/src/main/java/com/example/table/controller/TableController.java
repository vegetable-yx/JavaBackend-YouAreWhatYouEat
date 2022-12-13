package com.example.table.controller;

import com.example.table.dto.*;
import com.example.table.entity.DinningtableEntity;
import com.example.table.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1")

public class TableController {
    private TableService tableService;

    public TableController(TableService tableService)
    {
        this.tableService=tableService;
    }

    @RequestMapping("/testTable")
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

    @RequestMapping(value = "/table", method = RequestMethod.PUT)
    public ResponseEntity setTableStatus(@RequestBody PutTableParam info)
    {
        System.out.println(info);

        if(tableService.setTableStatus(info))
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/seat", method = RequestMethod.GET)
    public ResponseEntity<QueueTableRespond> assignSeat(@RequestParam BigInteger customer_number)
    {
        QueueTableRespond result=tableService.getQueueTable(customer_number);
        return new ResponseEntity<QueueTableRespond>(result, HttpStatus.OK);
    }

}
