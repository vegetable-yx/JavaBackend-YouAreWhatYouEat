package com.example.vip.Service.impl;

import com.example.vip.Entity.VipEntity;
import com.example.vip.Repository.VipRepository;
import com.example.vip.Service.VipService;
import com.example.vip.dto.*;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.Registration;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.bytebuddy.utility.JavaConstant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.*;


@Slf4j
@Service
public class VipServiceImpl implements VipService {

    @Resource
    VipRepository vipRepository;

    @Override
    public VipInfo getOneVipinfo(String name) {
        VipInfo result=new VipInfo();

        VipEntity vipEntity=vipRepository.findFirstByUserName(name);
        if(vipEntity==null){
            return  null;
        }
        result.setBalance(vipEntity.getBalance());
        result.setGender(vipEntity.getGender());
        result.setCredit(vipEntity.getCredit());
        result.setUser_name(name);

        return result;
    }

    @Override
    public GetAllVip getAllVip() {

        List<VipEntity> vipEntities=vipRepository.findAll();
        if(vipEntities.size()==0){
            return null;
        }
        GetAllVip allVIPInfo = new GetAllVip();
        ArrayList<VipInfo2> info=new ArrayList<>();



        AttrOfVIP attr1=new AttrOfVIP();
        attr1.setName("积分");
        AttrOfVIP attr2=new AttrOfVIP();
        attr2.setName("余额");





        ArrayList<BigInteger> integersLs1=new ArrayList<>();

        ArrayList<BigInteger> integersLs2=new ArrayList<>();

        ArrayList<String> nameLs=new ArrayList<>();

        for(VipEntity v:vipEntities){
             VipInfo2 vipInfo2=new VipInfo2();
             vipInfo2.setBalance(v.getBalance());
             if(v.getBirthday()!=null)
             {
                 vipInfo2.setBirthday(v.getBirthday().toString());
             }
             vipInfo2.setCredit(v.getCredit());
             vipInfo2.setGender(v.getGender());
             vipInfo2.setStatus("正常");
             vipInfo2.setUser_name(v.getUserName());
             info.add(vipInfo2);

          integersLs1.add(v.getCredit());
          integersLs2.add(BigInteger.valueOf(v.getBalance()));

          nameLs.add(v.getUserName());


        }
        attr1.setData(integersLs1);
        attr2.setData(integersLs2);

        ListOfVip listOfVip=new ListOfVip();
        Dictionary<String, ArrayList<String>> xaxis=new Hashtable<>();

        xaxis.put("categories", nameLs);
        listOfVip.setXaxis(xaxis);



        Dictionary<String,AttrOfVIP> dc1=new Hashtable<>();
        dc1.put("series",attr1);
        Dictionary<String, ListOfVip> dc2=new Hashtable<>();
        dc2.put("options",  listOfVip);

        ArrayList<Dictionary> summary=new ArrayList<>();
        summary.add(dc1);
        summary.add(dc2);

        Dictionary<String,AttrOfVIP> dc3=new Hashtable<>();
        dc3.put("series",attr2);
        Dictionary<String, ListOfVip> dc4=new Hashtable<>();
        dc4.put("options",  listOfVip);

        ArrayList<Dictionary> summary2=new ArrayList<>();
        summary2.add(dc3);
        summary2.add(dc4);


        allVIPInfo.setVips(info);
        allVIPInfo.setSummary(summary);
        allVIPInfo.setSummary2(summary2);

        return allVIPInfo;
    }

    @Override
    public HttpStatus addVip(PostAddVip vip) {
        VipEntity vipEntity=new VipEntity();

        vipEntity.setBirthday(Date.valueOf(vip.getBirthday()));
        vipEntity.setGender(vip.getGender());
        vipEntity.setPassword("233");
        vipEntity.setBalance(0);
        vipEntity.setUserName(vip.getPhoneNumber());
        vipEntity.setIsDefault("否");
        vipEntity.setCredit(BigInteger.valueOf(0));

        try {
            vipRepository.saveAndFlush(vipEntity);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }


    }

    @Override
    public HttpStatus updateVip(PutUpdateVip vip) {

        VipEntity vipEntity=vipRepository.findFirstByUserName(vip.getUserName());

        if(vipEntity==null){
          return HttpStatus.NO_CONTENT;
        }
        vipEntity.setBalance(vip.getBalance());
        vipEntity.setCredit(vip.getCredit());
        if(vip.getGender()!=null){
            vipEntity.setGender(vip.getGender());
        }
        if(vip.getBirthday()!=null){
            vipEntity.setBirthday(Date.valueOf(vip.getBirthday()));
        }
        try {
            vipRepository.saveAndFlush(vipEntity);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }


    }
}
