package com.example.orderlists.Controller;


import com.example.orderlists.Service.impl.OdLsServiceImpl;
import com.example.orderlists.dto.GetOrders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/orderlists")
public class OrderListsController {

    private final OdLsServiceImpl odLsService;


    public OrderListsController(OdLsServiceImpl odLsService) {
        this.odLsService = odLsService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllOrders(@RequestParam String begin,@RequestParam String end){
        GetOrders ls=odLsService.getOders(begin, end);
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }
}
