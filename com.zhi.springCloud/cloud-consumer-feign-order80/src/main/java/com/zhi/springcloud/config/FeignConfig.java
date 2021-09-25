package com.zhi.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        //设置 openFeign 的日志为详细日志
        return Logger.Level.FULL;
    }
}
