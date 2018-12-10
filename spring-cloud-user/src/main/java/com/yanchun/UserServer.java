package com.yanchun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UserServer.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServer.class, args);
    }
}
