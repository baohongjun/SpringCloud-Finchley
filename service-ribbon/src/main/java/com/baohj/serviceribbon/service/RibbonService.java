package com.baohj.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * author: baohj
 * created time: 2021/6/23
 */
@Service
public class RibbonService {

    @Autowired
    RestTemplate template;

    /**
     * @HystrixCommand 注解对该方法创建了熔断器的功能，
     * fallbackMethod指定了熔断方法：clientError
     */
    @HystrixCommand(fallbackMethod = "clientError")
    public String reqService(String name){
        return template.getForObject("http://EUREKA-CLIENT/client?name="+name,String.class);

    }

    public String clientError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}
