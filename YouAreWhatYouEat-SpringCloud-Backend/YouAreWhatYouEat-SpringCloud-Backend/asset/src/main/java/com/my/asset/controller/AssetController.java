package com.my.asset.controller;

import com.my.asset.entity.AssetsEntity;
import com.my.asset.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/asset")
public class AssetController {

    private AssetsService assetsService;

    @Autowired
    public AssetController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @GetMapping("test")
    @ResponseBody
    @CrossOrigin
    public AssetsEntity getAssets(@RequestParam String assetsType) {
        return assetsService.getAssets(assetsType);
    }

}
