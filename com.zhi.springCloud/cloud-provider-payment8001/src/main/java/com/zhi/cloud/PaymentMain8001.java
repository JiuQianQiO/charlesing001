package com.zhi.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  // 表示自己是 eureka 的客户端
@EnableDiscoveryClient // 获取服务发现Discovery
public class PaymentMain8001 {
    public static void main(String[] args) {
      SpringApplication.run(PaymentMain8001.class,args);
    }
}
