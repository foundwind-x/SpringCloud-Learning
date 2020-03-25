package com.fz.springcloud.gateway.controller;

import com.fz.springcloud.gateway.vo.ResponseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @ClassName controller
 * @Description 测试hystrix的fallback功能所用到的controller
 * @Author fangzheng
 * @Date 2019/9/6 15:07
 * @Version V1.0
 */
@RestController
public class FallbackController {

    /**
     * 该方法对应于spring.cloud.gateway.default-filters中配置的全局默认filter中的Hystrix的fallbackUri。
     * @return
     */
    @RequestMapping("/defaultFallback")
    public Mono<ResponseMessage> fallback() {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(500);
        responseMessage.setMessage("defaultFallback：服务暂时不可用！");
        return Mono.just(responseMessage);
    }

    /**
     * 该方法对应于DemoRouteLocator中路由hystrix_fallback_route的fallbackUri。
     * Mono是webflux的类，响应式编程的写法
     * @return
     */
    @RequestMapping("/hystrixFallback")
    public Mono<ResponseMessage> hystrixFallback() {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(500);
        responseMessage.setMessage("hystrixFallback：服务暂时不可用");
        return Mono.just(responseMessage);
    }
}
