package com.example.employee.service;

import com.example.employee.dto.TokenInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "authen", url = "localhost:18084/api/v1")
public interface AuthenClient {
    @RequestMapping(value = "tokenLegality", method = RequestMethod.GET)
    public ResponseEntity<TokenInfo> checkToken(
            @RequestParam(required = false) String token
    );
}
