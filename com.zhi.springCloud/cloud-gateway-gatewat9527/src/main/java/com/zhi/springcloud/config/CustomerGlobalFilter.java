package com.zhi.springcloud.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomerGlobalFilter implements GlobalFilter, Ordered {
    /**
     *  这个方法用于设置过滤器链
     * @param exchange 类似 HttpServlet 可以获取和设置众多请求、响应参数
     * @param chain  GatewayFilterChain 网关的过滤器链
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String name = exchange.getRequest().getQueryParams().getFirst("name");//获取请求参数，key 为 name 的值
        if (name == null){
            System.out.println("用户名为： null，非法用户");
            // 如果没有获取到用户名，给客户端返回当前请求是不被接受的
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        // 如果成功，将过滤器链返回给下一个过滤器

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 设置加载过滤器的顺序，数字越小，优先级越高
        return 0;
    }
}
