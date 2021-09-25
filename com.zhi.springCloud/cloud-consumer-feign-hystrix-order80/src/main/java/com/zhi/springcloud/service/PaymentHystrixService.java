package com.zhi.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
// 配置当前类中的方法是那个服务器集群提供的，value是服务器集群的名称，
// fallback 是配置当前类发生异常（就是提供服务的服务器宕机），由那个类中的方法提供 fallback（降级处理），这个类必须是实现 当前接口
// 总之就是，当前接口是服务提供者提供的所有方法，服务提供者发生错误，由这个接口的实现类提供服务降级处理
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") int id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") int id);
}
