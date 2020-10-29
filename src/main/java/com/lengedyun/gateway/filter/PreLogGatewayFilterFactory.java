package com.lengedyun.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @title: PreLogGatewayFilterFactory
 * @description: 自定义过滤器工厂，约定以GatewayFilterFactory结尾
 * @auther: 张健云
 * @date: 2020/10/29 22:02
 */

@Slf4j
@Component
public class PreLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
//        log.info("请求进来了");//在此处打印项目启动时也会打印 因为此处相当于bean初始化
        return ((exchange, chain) -> {
            log.info("请求进来了参数{},{}",config.getName(),config.getValue());//在此处只会在请求是被调用
            ServerHttpRequest modifyRequest = exchange.getRequest()
                    .mutate()
                    .build();
            ServerWebExchange modifyExchange = exchange.mutate().request(modifyRequest).build();
            return chain.filter(modifyExchange);
        });
    }
}
