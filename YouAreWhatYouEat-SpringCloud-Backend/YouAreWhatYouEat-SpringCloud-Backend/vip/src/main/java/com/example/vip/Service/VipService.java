package com.example.vip.Service;

import com.example.vip.dto.GetAllVip;
import com.example.vip.dto.PostAddVip;
import com.example.vip.dto.PutUpdateVip;
import com.example.vip.dto.VipInfo;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;

public interface VipService {

    public VipInfo getOneVipinfo(String name);

    public GetAllVip getAllVip();

    public HttpStatus addVip(PostAddVip vip);

    public HttpStatus updateVip(PutUpdateVip vip);
    
}
