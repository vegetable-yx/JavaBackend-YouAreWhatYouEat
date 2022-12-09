package com.example.ingredient.Service;

import com.example.ingredient.dto.GetIng;
import com.example.ingredient.dto.GetIngRecord;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;

public interface IngService {

    public GetIng getIngMessage(String name);

    public GetIng getIngMessage();

    public GetIngRecord getIngRecord();

    
}
