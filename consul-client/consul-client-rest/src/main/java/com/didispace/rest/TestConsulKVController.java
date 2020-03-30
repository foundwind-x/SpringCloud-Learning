package com.didispace.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestConsulKVController
 * @Description 测试consul配置中心功能
 * @Author fangzheng
 * @Date 2019/10/23 11:18
 * @Version V1.0
 */
@RestController
public class TestConsulKVController {

    /**
     * 当使用consul kv时，spring.redis.host为consul中配置的属性值；配置中心的属性优先级大于application.yml，小于bootstrap.yml
     */
    @Value("${spring.redis.host}")
    private String redisHost;

    @GetMapping("consul/config")
    public String getConfig(){
        return redisHost;
    }
}
