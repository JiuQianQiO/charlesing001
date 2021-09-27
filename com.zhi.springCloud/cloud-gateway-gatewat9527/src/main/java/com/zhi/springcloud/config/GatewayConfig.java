package com.zhi.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    /**
     * 方法的返回值的类型是：RouteLocator   返回的时候  routes.build（）
     * 参数类型：RouteLocatorBuilder routeLocatorBuilder
     *  routes.route("id 名称，必须唯一",r->r.path("访问地址后面的路径").uri("要访问地址的 uri")).build();
     *  在当前工程的访问路径后面加上 path 的值就是   uri 中的地址：eg： http://localhost:9527/guonei
     */
    public RouteLocator CustomerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_baidu",r->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
