package com.fz.consumer.controller;

import com.fz.api.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Description demo
 * @Author fangzheng
 * @Date 2020/3/4 23:10
 * @Version V1.0
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/say/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return demoService.sayHello(name);
    }
}
