package com.baohj.servicefeign.service.impl;

import com.baohj.servicefeign.service.FeignService;
import org.springframework.stereotype.Component;

/**
 * author: baohj
 * created time: 2021/6/24 14:43
 * description
 */
@Component
public class FeignServiceImpl implements FeignService {
    @Override
    public String sayHiFromClientOne(String name) {

        return "say,sorry "+name;
    }
}
