package com.didispace.service;

public interface IRedissonTestService {

    public boolean decrementWithLock(String key);
}
