package com.didispace.config.runner;

import com.didispace.common.constant.LogConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName InitialCacheApplicationRunner
 * @Description 启动时初始化缓存
 * @Author fangzheng
 * @Date 2019/8/30 15:57
 * @Version V1.0
 */
@Slf4j
@Order(2)  //越小越靠前
@Component
public class InitialCacheApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(LogConst.AROUND,"InitialCacheApplicationRunner start");


        log.info(LogConst.AROUND,"InitialCacheApplicationRunner end");
    }
}
