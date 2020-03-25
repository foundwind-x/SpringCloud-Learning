package com.fz.springcloud.gateway.route;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName DemoRouteLocator
 * @Description 定义springCloud gateway路由
 * @Author fangzheng
 * @Date 2019/9/6 14:56
 * @Version V1.0
 */
@Configuration
public class DemoRouteLocator {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                //根据PATH路由,测试连接：http://localhost:8888/get/headers
                .route("path_route", r -> r.path("/get/**")
                        .filters(f -> f.addRequestHeader("Hello", "World").stripPrefix(1))
                        .uri("http://httpbin.org"))
                //根据HOST路由，Headers中设置Host=aaa.myhost.org
                .route("host_route", r -> r.host("*.myhost.org")
                        .uri("http://httpbin.org"))
                //使用rewritePath
                .route("rewrite_route", r -> r.host("*.rewrite.org")
                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)","/${segment}"))
                        .uri("http://httpbin.org"))
                //使用hystrix
                .route("hystrix_route", r -> r.host("*.hystrix.org")
                        .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
                        .uri("http://httpbin.org"))
                //使用hystrix fallback，yml文件中已经配置过了
//                .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
//                        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//                        .uri("http://httpbin.org"))
                .build();
    }

    /**
     * 注入自定义限流类型（针对 ip 限流）
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(final ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
            }
        };
    }

    /**
     * 注入自定义限流类型（根据接口的URI进行限流）
     * @return
     */
    /*@Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }*/

    /**
     * 根据用户ID限流，请求路径中必须携带userId参数
     * @return
     */
   /* @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }*/

}
