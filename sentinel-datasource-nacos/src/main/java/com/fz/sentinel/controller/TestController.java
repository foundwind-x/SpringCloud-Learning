package com.fz.sentinel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description test
 * @Author fangzheng
 * @Date 2020/3/20 1:25
 * @Version V1.0
 */
@Slf4j
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "welcome to world!";
    }
}
