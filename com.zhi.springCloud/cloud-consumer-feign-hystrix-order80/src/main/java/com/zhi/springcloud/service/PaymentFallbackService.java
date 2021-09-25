package com.zhi.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(int id) {
        return "----------PaymentFallbackService paymentInfo_OK  ----Fallback ,o(*￣▽￣*)ブ";
    }

    @Override
    public String paymentInfo_TimeOut(int id) {
        return "----------PaymentFallbackService paymentInfo_TimeOut  ----Fallback ,o(*￣▽￣*)ブ";
    }
}
