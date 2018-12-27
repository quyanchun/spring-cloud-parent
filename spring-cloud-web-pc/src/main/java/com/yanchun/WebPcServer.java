package com.yanchun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author quyanchun
 * @Date 2018/12/17
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class WebPcServer {
    public static void main(String[] args) {
        SpringApplication.run(WebPcServer.class, args);
    }
}
