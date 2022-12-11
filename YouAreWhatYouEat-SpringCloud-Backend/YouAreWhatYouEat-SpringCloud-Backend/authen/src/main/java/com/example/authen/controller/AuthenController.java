package com.example.authen.controller;

import com.example.authen.dto.TokenInfo;
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
    public ResponseEntity<TokenInfo> checkToken(
            @RequestParam(required = false) String token
    ) throws IOException {
        System.out.println("Hi");
        try {
            MyToken myToken = myTokenService.checkToken(token);
            TokenInfo info = new TokenInfo();
            info.setActive(myToken.isActive());
            info.setUsername(myToken.getUsername());
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
