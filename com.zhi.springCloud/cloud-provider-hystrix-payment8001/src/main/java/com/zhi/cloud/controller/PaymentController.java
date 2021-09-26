package com.zhi.cloud.controller;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhi.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") int id){

        String result = paymentService.paymentInfo_OK(id);
        log.info("ok>>>>>>>>>>>>>>>>>>>>result"+result+":::     :serverPort"+serverPort);
        return  result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") int id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("timeOut>>>>>>>>>>>>>>>>result: "+result+":::     :serverPort"+serverPort);
        return result;
    }
//==========服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") int id){
        String circuitBreak = paymentService.paymentCircuitBreak(id);
        log.info("*******circuitBreaker"+circuitBreak);
        return circuitBreak;
    }

}
