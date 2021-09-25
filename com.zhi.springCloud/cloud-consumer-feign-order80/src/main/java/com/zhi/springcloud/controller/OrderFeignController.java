package com.zhi.springcloud.controller;

import com.zhi.springcloud.entities.CommonResult;
import com.zhi.springcloud.entities.Payment;
import com.zhi.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/paymentfeign/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") int id){
        return paymentFeignService.getPaymentById(id);
    }
}
