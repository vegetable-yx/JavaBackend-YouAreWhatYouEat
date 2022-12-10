package com.example.ingredient.Service;

import com.example.ingredient.dto.GetIng;
import com.example.ingredient.dto.GetIngRecord;
import com.example.ingredient.dto.PostIng;
import com.example.ingredient.dto.PostIngRrd;
import oracle.jdbc.proxy.annotation.Post;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;

public interface IngService {

    public GetIng getIngMessage(String name);

    public GetIng getIngMessage();

    public GetIngRecord getIngRecord();

    HttpStatus deleteIng(BigInteger id);

    HttpStatus deleteIngRecord(BigInteger id);

    public HttpStatus addIng(PostIng ing);

    public HttpStatus updateIng(PostIng ing);

    public HttpStatus addIngRecord(PostIngRrd record);

    public HttpStatus updateIngRecord(PostIngRrd record);

}
