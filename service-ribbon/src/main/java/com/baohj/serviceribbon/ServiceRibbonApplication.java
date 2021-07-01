package com.baohj.serviceribbon;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  //服务中心注册
@EnableHystrix          //开启Hystrix
@EnableHystrixDashboard //开启hystrixDashboard
public class ServiceRibbonApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

    @Bean               //程序的ioc注入一个bean
    @LoadBalanced       //表明这个restRemplate开启负载均衡的功能
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 开启hystrixDashboard后，配置HystrixMetricsStreamServlet
     * url；http://localhost:8764/actuator/hystrix.stream
     * 以上配置是由于springboot的版本是属于2.xx以上
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
