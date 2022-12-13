package com.example.ingredientrecords.Controller;

import com.example.ingredientrecords.Service.impl.IngServiceImpl;
import com.example.ingredientrecords.dto.GetIngRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/ingredientRecords")

public class IngRecordController {

    private final IngServiceImpl ingService;

    @Autowired
    public IngRecordController(IngServiceImpl ingService) {
        this.ingService = ingService;
    }


    @GetMapping("")
    public ResponseEntity<Object> getAllIngsRecord(){
        List<GetIngRecords> ls= ingService.getIngRecordsList();
        System.out.println(1111);
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }

}
