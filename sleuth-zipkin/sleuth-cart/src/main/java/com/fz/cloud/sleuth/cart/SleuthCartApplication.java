package com.fz.cloud.sleuth.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.fz.cloud")
@SpringBootApplication(scanBasePackages = "com.fz.cloud")
public class SleuthCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthCartApplication.class, args);
    }

}
