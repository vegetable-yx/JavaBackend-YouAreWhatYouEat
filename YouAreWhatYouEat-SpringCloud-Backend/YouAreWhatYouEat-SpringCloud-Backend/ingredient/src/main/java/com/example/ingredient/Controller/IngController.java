package com.example.ingredient.Controller;

import com.example.ingredient.Service.impl.IngServiceImpl;
import com.example.ingredient.dto.GetIng;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/ingredient")
public class IngController {
    private final IngServiceImpl ingService;

    @Autowired
    public IngController(IngServiceImpl ingService) {
        this.ingService = ingService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllIngs(@RequestParam Optional<String> ingrName){
        GetIng ls;
        if(ingrName.isEmpty()){
             ls=ingService.getIngMessage();
        }else{
            ls=ingService.getIngMessage(ingrName.get());
        }
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }




    }

}
