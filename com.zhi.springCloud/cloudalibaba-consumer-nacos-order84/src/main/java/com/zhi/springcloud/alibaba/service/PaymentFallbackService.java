package com.zhi.springcloud.alibaba.service;

import com.zhi.springcloud.alibaba.entities.CommonResult;
import com.zhi.springcloud.alibaba.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Integer id) {
        return new CommonResult<>(444,"服务降级返回的方法，---PaymentFallbackService",new Payment(id,"Feigneror"));
    }
}
