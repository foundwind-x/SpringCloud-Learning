package com.fz.provider.controller;

import com.fz.api.constant.ApiPathConstant;
import com.fz.api.service.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Description DemoService实现类
 * @Author fangzheng
 * @Date 2020/3/4 22:35
 * @Version V1.0
 */
@RestController
public class DemoController implements DemoService {

    @Value("${server.port}")
    private Integer port;

    @GetMapping(value = ApiPathConstant.HELLO)
    public String sayHello(String name) {
        return "Hello " + name + ", I'm provider from " + port;
    }
}
