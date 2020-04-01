package com.fz.cloud.sleuth.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.fz.cloud")
@SpringBootApplication(scanBasePackages = "com.fz.cloud")
public class SleuthSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthSearchApplication.class, args);
    }

}
