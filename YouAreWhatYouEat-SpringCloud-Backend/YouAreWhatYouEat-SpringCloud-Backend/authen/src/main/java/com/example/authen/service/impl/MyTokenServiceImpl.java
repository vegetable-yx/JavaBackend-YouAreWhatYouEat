package com.example.authen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.authen.entity.MyToken;

import com.example.authen.service.MyTokenService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class MyTokenServiceImpl implements MyTokenService {
    @Value("${myConfiguration.casdoorPath}")
    private String baseUrl;
    @Value("${myConfiguration.authorization}")
    private String authorization;
    private HttpClient client = new HttpClient();

    public MyToken checkToken(String token) throws IOException {
        client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        PostMethod postMethod = new PostMethod(baseUrl + "api/login/oauth/introspect");

        postMethod.addRequestHeader("Content-type",
                "application/x-www-form-urlencoded");
        postMethod.addRequestHeader("accept",
                "application/json");
        postMethod.addRequestHeader("Authorization",
                authorization);

        postMethod.addParameter("token", token);
        postMethod.addParameter("token_type_hint", "access_token");


        client.executeMethod(postMethod);
        byte[] responseBody = postMethod.getResponseBody();
        String s = new String(responseBody);

        JSONObject response = JSONObject.parseObject(s);

        ModelMapper modelMapper = new ModelMapper();
        MyToken myToken = modelMapper.map(response, MyToken.class);

        return myToken;
    }
}
