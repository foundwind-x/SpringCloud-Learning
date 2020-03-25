package com.fz.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动参数：-Dproject.name=spring-cloud-gateway -Dcsp.sentinel.dashboard.server=localhost:8080 -Dcsp.sentinel.api.port=8720 -Dcsp.sentinel.app.type=1
 */
@SpringBootApplication
public class SpringCloudGatewaySentinelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewaySentinelApplication.class, args);
    }

}
