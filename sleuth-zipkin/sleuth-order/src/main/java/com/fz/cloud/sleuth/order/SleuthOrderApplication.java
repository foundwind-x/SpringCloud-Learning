package com.fz.cloud.sleuth.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName SleuthOrderApplication
 * @Description application
 * @Author fangzheng
 * @Date 2020/3/31 11:20
 * @Version V1.0
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.fz.cloud")
@SpringBootApplication(scanBasePackages = "com.fz.cloud")
public class SleuthOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthOrderApplication.class, args);
    }
}
