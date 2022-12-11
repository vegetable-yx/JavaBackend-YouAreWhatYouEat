package com.example.vip.Controller;


import com.example.vip.Service.VipService;
import com.example.vip.dto.GetAllVip;
import com.example.vip.dto.PostAddVip;
import com.example.vip.dto.PutUpdateVip;
import com.example.vip.dto.VipInfo;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/vips")
@CrossOrigin
public class VipController {
    private final VipService vipService;

    @Autowired
    public VipController(VipService vipService) {
        this.vipService = vipService;
    }


    @GetMapping("")
    public ResponseEntity<Object> getOneVip(@RequestParam Optional<String> user_name){
        VipInfo ls;
        if( user_name.isEmpty()){
          ls=null;
        }else{
            ls=vipService.getOneVipinfo( user_name.get());
        }
        if(ls==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(ls);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllVip(){
        GetAllVip getAllVip=vipService.getAllVip();


        if(getAllVip==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            return ResponseEntity.ok(getAllVip);
        }
    }

    @PutMapping("")
    public ResponseEntity<Object> putUpdateVip(@RequestBody PutUpdateVip p) {
        System.out.println(p);
        return new ResponseEntity<>(vipService.updateVip(p));
    }

    @PostMapping("")
    public ResponseEntity<Object> postAddVip(@RequestBody PostAddVip p) {
        System.out.println(p);
        return new ResponseEntity<>(vipService.addVip(p));
    }


}
