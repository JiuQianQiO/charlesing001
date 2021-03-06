package com.zhi.cloud.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/*// 开启 Rest Template的负载均衡机制*/
@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced   //在这里把 默认的负载均衡给注释掉，自己定义负载均衡
    public RestTemplate getRestTemplate(RestTemplateBuilder builder){
        return builder.build();
//        return new RestTemplate();
    }
}
