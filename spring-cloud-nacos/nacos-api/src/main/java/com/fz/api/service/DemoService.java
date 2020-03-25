package com.fz.api.service;

import com.fz.api.constant.ApiPathConstant;
import com.fz.api.constant.FeignClientConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * demo rpc service
 * @author fangzheng
 */
@FeignClient(value = FeignClientConstant.FEIGN_CLIENT,path = FeignClientConstant.FEIGN_CLIENT_PATH)
public interface DemoService {

    @GetMapping(value = ApiPathConstant.HELLO)
    public String sayHello(@RequestParam(name = "name")String name);
}
