package com.fz.springcloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName IgnoreAuthGlobalFilterFactor
 * @Description 自定义gateway:routes:filters属性
 * @Author fangzheng
 * @Date 2019/11/14 9:46
 * @Version V1.0
 */
@Component
public class IgnoreAuthGlobalFilterFactor extends AbstractGatewayFilterFactory<IgnoreAuthGlobalFilterFactor.Config> {

    public IgnoreAuthGlobalFilterFactor() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return this::filter;
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(AuthGlobalFilter.ATTRIBUTE_AUTH_GLOBAL_FILTER, true);
        return chain.filter(exchange);
    }

    public static class Config {

    }

    @Override
    /**
     * gateway配置中routes:filters的属性名称
     */
    public String name() {
        return "AuthGlobalFilter";
    }
}
