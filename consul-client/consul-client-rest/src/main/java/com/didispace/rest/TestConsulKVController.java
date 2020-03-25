package com.didispace.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestConsulKVController
 * @Description TODO
 * @Author fangzheng
 * @Date 2019/10/23 11:18
 * @Version V1.0
 */
@RestController
public class TestConsulKVController {

   //@Value("${spring.redis.host}")
    private String redisHost;

    @GetMapping("consul/config")
    public String getConfig(){
        return redisHost;
    }
}
