package com.baohj.servicefeign.service;

import com.baohj.servicefeign.service.impl.FeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author: baohj
 * created time: 20210623175600
 */

/**
 * value=服务名 指定调用哪个服务,比如在代码中调用了eureka-client服务的“/client”接口
 * fallback: 指定需要断路器的类，需实现该接口
 */
@FeignClient(value = "eureka-client",fallback = FeignServiceImpl.class)
@Service
public interface FeignService {

    @RequestMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
