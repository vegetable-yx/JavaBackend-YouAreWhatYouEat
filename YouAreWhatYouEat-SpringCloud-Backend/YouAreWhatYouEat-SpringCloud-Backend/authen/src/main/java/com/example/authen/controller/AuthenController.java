package com.example.authen.controller;

import com.example.authen.entity.MyToken;
import com.example.authen.service.MyTokenService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin
public class AuthenController {
    @Resource
    private MyTokenService myTokenService;

    @RequestMapping(value = "tokenLegality", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkToken(
            @RequestParam(required = false) String token
    ) throws IOException {
        System.out.println("Hi");
        try {
            MyToken myToken = myTokenService.checkToken(token);
            if (myToken.isActive()) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
