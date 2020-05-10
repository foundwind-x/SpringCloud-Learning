package com.didispace.service.impl;

import com.didispace.service.IRedissonTestService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedissonTestServiceImpl
 * @Description 测试分布式锁Redisson使用
 * @Author fangzheng
 * @Date 2019/9/26 15:54
 * @Version V1.0
 */
@Slf4j
@Service
public class RedissonTestServiceImpl implements IRedissonTestService {
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean decrementWithLock(String key) {
        UUID uuid = UUID.randomUUID();
        log.info("==access uuid=="+uuid);
        RLock lock = redissonClient.getLock(key);
        try {
            //带timeout参数，强制锁释放时间，防止死锁
            lock.lock(10000, TimeUnit.MILLISECONDS);
            log.info("==get lock uuid=="+uuid);
            Thread.sleep(500);
            log.info("==release lock uuid=="+uuid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return false;
    }
    /*public boolean decrementWithLock(String key) {
        boolean result = false;
        UUID uuid = UUID.randomUUID();
        log.info("==access uuid=="+uuid);
        RLock lock = redissonClient.getLock(key);
        boolean hasLock;
        try {
            //尝试获取分布式锁:第一个参数是等待时间,第二个参数强制锁释放时间
            hasLock = lock.tryLock(10000, 15000, TimeUnit.MILLISECONDS);
            if(hasLock){
                log.info("==get lock uuid=="+uuid);
                Thread.sleep(200);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("==release lock uuid=="+uuid);
            // 解锁
            lock.unlock();
        }

        return result;
    }*/

}
