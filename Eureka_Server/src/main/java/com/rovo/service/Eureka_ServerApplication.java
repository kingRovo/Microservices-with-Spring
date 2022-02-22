package com.rovo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class Eureka_ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_ServerApplication.class,args);
    }
}
