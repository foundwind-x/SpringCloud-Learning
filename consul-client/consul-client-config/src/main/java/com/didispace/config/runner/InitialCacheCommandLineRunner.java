package com.didispace.config.runner;

import com.didispace.common.constant.LogConst;
import com.didispace.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName InitialCacheCommandLineRunner
 * @Description 启动时初始化缓存
 * @Author fangzheng
 * @Date 2019/8/30 11:17
 * @Version V1.0
 */
@Component
@Order(1)  //越小越靠前
@Slf4j
public class InitialCacheCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public void run(String... args) throws Exception {
        log.info(LogConst.AROUND,"InitialCacheCommandLineRunner start");

        redisUtil.set("TestCache:consul-client:startup_time",new Date());

        log.info(LogConst.AROUND,"InitialCacheCommandLineRunner end");
    }
}
