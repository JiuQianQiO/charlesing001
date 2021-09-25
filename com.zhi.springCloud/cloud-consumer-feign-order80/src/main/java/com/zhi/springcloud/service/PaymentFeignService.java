package com.zhi.springcloud.service;

import com.zhi.springcloud.entities.CommonResult;
import com.zhi.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //注解中的值是 微服务集群中的名称
public interface PaymentFeignService {
    // 在接口中定义请求地址，直接请求服务提供者提供的服务
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") int id);
}
