package com.didispace.config.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @ClassName RedissonConfig
 * @Description Redisson configuration
 * @Author fangzheng
 * @Date 2019/9/26 11:31
 * @Version V1.0
 */
@Configuration
public class RedissonConfig {

    /**
     * 单实例普通分布式锁使用
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() throws IOException {
        Config config = Config.fromYAML(new ClassPathResource("redisson-single.yml").getInputStream());
        //减少连接数
        config.useSingleServer().setConnectionMinimumIdleSize(10);
        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }
}
