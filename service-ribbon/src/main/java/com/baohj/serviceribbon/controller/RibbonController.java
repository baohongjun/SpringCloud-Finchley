package com.baohj.serviceribbon.controller;

import com.baohj.serviceribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: baohj
 * created time: 2021/6/23
 */
@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @RequestMapping("/hi")
    public String getHiServiceInfo(@RequestParam String name){

        return ribbonService.reqService(name);
    }

}
