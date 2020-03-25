package com.didispace.rest;

import com.didispace.common.constant.RedisConst;
import com.didispace.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestCacheController
 * @Description 测试redis缓存
 * @Author fangzheng
 * @Date 2019/9/12 17:21
 * @Version V1.0
 */
@RestController
@Slf4j
public class TestCacheController {

    @Autowired
    RedisUtil redisUtil;

    @Cacheable(value = "TestCache")
    @GetMapping("cache/get")
    public String getCache(@RequestParam(name = "id",required = true) String id){
        log.info("====access in getCache====");

        return "未走缓存!"+id;
    }

    @GetMapping("cache/set")
    public Boolean setAAA(@RequestParam(name = "id",required = true) String id){
        boolean result = redisUtil.set("TestCache::setCache:"+id, "测试spring cache!" + id, RedisConst.TTL);

        return result;
    }
}
