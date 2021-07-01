package com.baohj.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组件
 * 在spring cloud config 组件中，分两个角色，
 * 一是config server，二是config client
 * spring cloud config组件支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中
 * 本例以公开的远程git仓库为例的测试
 */
@SpringBootApplication
@EnableConfigServer         //开启配置服务器的功能
@EnableDiscoveryClient
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
