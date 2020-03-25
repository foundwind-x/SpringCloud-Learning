package com.fz.springcloud.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.fz.springcloud.gateway.vo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ClassName AuthGlobalFilter
 * @Description 全局过滤器做鉴权处理
 * @Author fangzheng
 * @Date 2019/11/14 9:42
 * @Version V1.0
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    public final static String ATTRIBUTE_AUTH_GLOBAL_FILTER = "@AuthGlobalFilter";
    public final static int SUCCESS_STATUS = 200;
    public final static int FAILURE_STATUS = 500;

//    @Autowired
//    private TokenService tokenService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * 网关配置routes:filters包含IgnoreAuthGlobalFilter属性的跳过检测
         */
        if (exchange.getAttribute(ATTRIBUTE_AUTH_GLOBAL_FILTER) == null) {
            return chain.filter(exchange);
        }
        log.info("====execute AuthGlobalFilter====");
        /**
         * 权限校验
         */
        //TODO
        /*HttpHeaders headers = exchange.getRequest().getHeaders();
        List<String> tokens = headers.get("X-Access-Token");
        if(CollectionUtils.isEmpty(tokens) || StringUtils.isEmpty(tokens.get(0))){
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(FAILURE_STATUS);
            responseMessage.setMessage("Token为空");
            return print(exchange, responseMessage);
        }
        String token = tokens.get(0);
        try {
            Result<LoginUser> result = tokenService.checkUserTokenIsEffect(token);
            if(result.getCode() != SUCCESS_STATUS){
                ResponseMessage responseMessage = new ResponseMessage();
                responseMessage.setStatus(FAILURE_STATUS);
                responseMessage.setMessage(result.getMessage());
                return print(exchange, responseMessage);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(FAILURE_STATUS);
            responseMessage.setMessage("Token校验超时");
            return print(exchange, responseMessage);
        }*/

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //在GatewayFilter之后执行
        return 10;
    }

    private Mono<Void> print(ServerWebExchange exchange,ResponseMessage message){
        ServerHttpResponse response = exchange.getResponse();
        byte[] datas = JSON.toJSONString(message).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        return response.writeWith(Mono.just(buffer));
    }
}
