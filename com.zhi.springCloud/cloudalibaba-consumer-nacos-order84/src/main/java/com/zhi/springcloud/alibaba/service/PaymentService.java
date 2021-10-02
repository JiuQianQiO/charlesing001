package com.zhi.springcloud.alibaba.service;

import com.zhi.springcloud.alibaba.entities.CommonResult;
import com.zhi.springcloud.alibaba.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
//value: 添加服务提供者的微服务名称，下面的请求地址都是服务提供者中定义的； fallback：服务降级类，调用当前类中的方法进行兜底。
public interface PaymentService {
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Integer id) ;
}
