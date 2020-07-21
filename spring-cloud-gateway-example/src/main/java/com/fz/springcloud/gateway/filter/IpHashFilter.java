package com.fz.springcloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IpHashFilter
 * @Description IpHashFilter：没整合注册中心的用法
 * @Author fangzheng
 * @Date 2020/4/26 11:16
 * @Version V1.0
 */
//@Component
public class IpHashFilter extends RouteToRequestUrlFilter{
    private static List<String> mUrlList = new ArrayList<String>() {
        {
            add("http://10.0.0.1");
            add("http://10.0.0.2");
            add("http://10.0.0.3");
        }
    };

    @Override
    public int getOrder() {
        return 10001;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String address = request.getRemoteAddress().getAddress().getHostAddress();
        int num = Math.abs(address.hashCode()) % mUrlList.size();
        URI mergedUrl = URI.create(mUrlList.get(num));
        exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, mergedUrl);
        return chain.filter(exchange);
    }
}
