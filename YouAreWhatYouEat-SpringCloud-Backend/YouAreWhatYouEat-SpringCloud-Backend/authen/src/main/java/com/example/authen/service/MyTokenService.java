package com.example.authen.service;

import com.example.authen.entity.MyToken;

import java.io.IOException;

public interface MyTokenService {
    public MyToken checkToken(String token) throws IOException;
}
