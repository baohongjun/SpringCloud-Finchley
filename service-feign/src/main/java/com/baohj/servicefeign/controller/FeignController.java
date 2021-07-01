package com.baohj.servicefeign.controller;

import com.baohj.servicefeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: baohj
 * created time: 20210623180200
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    FeignService feignService;

    @RequestMapping(value ="/hi")
    public String getHiInfoByFeign(String name){
        return feignService.sayHiFromClientOne(name);
    }

}
