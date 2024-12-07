package com.gcq.gcqgateway;


import com.gcq.gcqcommon.service.UserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Order {


    @DubboReference(scope = "remote")
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                 //1.统一日志
                ServerHttpRequest request = exchange.getRequest();
                log.info("请求路径：{}",request.getPath());
                log.info("请求参数：{}",request.getQueryParams());
                log.info("请求头：{}",request.getHeaders());
                log.info("请求方法：{}",request.getMethod());
                String hostString = request.getLocalAddress().getHostString();
                log.info("请求ip：{}",hostString);
        UserInterfaceInfoService userInterfaceInfoService1 = userInterfaceInfoService;

        //2.黑自名单
                //4.用户鉴权（判断ak、sk是否合法）
                 //5。请求的模拟接口是否存在？
                //6。请求转发，调用模拟接口
                //7.响应日志
                //8。调用成功，接口调用次数+1
                //9,调用失败，返回一个规范的错误码
        return chain.filter(exchange);
    }

    @Override
    public int value() {
        return 0;
    }
}
