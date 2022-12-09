package com.example.ingredient.Service;

import com.example.ingredient.dto.GetIng;
import com.example.ingredient.dto.GetIngRecord;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;

public interface IngService {

    public GetIng getIngMessage(String name);

    public GetIng getIngMessage();

    public GetIngRecord getIngRecord();

    HttpStatus deleteIng(BigInteger id);

    HttpStatus deleteIngRecord(BigInteger id);
}
