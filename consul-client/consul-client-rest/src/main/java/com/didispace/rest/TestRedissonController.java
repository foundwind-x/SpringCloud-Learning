package com.didispace.rest;

import com.didispace.service.IRedissonTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestRedissonController
 * @Description TODO
 * @Author fangzheng
 * @Date 2019/9/26 17:09
 * @Version V1.0
 */
@RestController
@RequestMapping("redisson")
public class TestRedissonController {
    @Autowired
    IRedissonTestService redissonTestService;

    @GetMapping("decrement")
    public Boolean decrementWithLock(){
        boolean b = redissonTestService.decrementWithLock("TestCache:decrement_lock");

        return b;
    }
}
