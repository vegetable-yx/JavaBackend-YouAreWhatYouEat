package com.example.table.controller;

import com.example.table.entity.DinningtableEntity;
import com.example.table.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        DinningtableEntity table=this.tableService.findByTableId();
        return table;
    }
}
