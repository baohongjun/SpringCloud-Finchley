package com.baohj.eurekaclient;

import brave.sampler.Sampler;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author DELL
 */
@SpringBootApplication
@EnableEurekaClient         //表明自己是一个eurekaclient.
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class EurekaClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    private String port;

    @RequestMapping("/client")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam String name){

        return "hi 2021 "+name+",i am from port:" +port;
    }
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

    /**
     * 服务追踪组件zipkin
     * Spring Cloud Sleuth 主要功能就是在分布式系统中提供追踪解决方案，并且兼容支持了 zipkin，只需要在pom文件中引入相关依赖
     * 案例主要有三个工程组成:一个server-zipkin,它的主要作用使用ZipkinServer 的功能，收集调用数据，并展示；
     * (spring Cloud为F版本的时候，已经不需要自己构建Zipkin Server了，只需要运行相关jar即可)
     * 一个service-hi,对外暴露hi接口；一个service-miya,对外暴露miya接口；
     * 这两个service可以相互调用；并且只有调用了，server-zipkin才会收集数据的
     */
//    private static final Logger LOG = Logger.getLogger(EurekaClientApplication.class.getName());
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Bean
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }
//
//    @RequestMapping("/hi")
//    public String callHome(){
//        LOG.log(Level.INFO, "calling trace eureka-client  ");
//        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
//    }
//    @RequestMapping("/info")
//    public String info(){
//        LOG.log(Level.INFO, "calling trace eureka-client ");
//
//        return "i'm eureka-client";
//
//    }
//
//   @Bean
//    public Sampler defaultSampler() {
//        return Sampler.ALWAYS_SAMPLE;
//    }

}
