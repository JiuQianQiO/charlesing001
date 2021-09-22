package com.zhi.cloud.controller;

import com.zhi.cloud.entities.CommonResult;
import com.zhi.cloud.entities.Payment;
import com.zhi.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("######插入结果成功："+result);
        if (result>0){
            return new CommonResult(200,"插入结果成功",result);
        }else {
            return new CommonResult(444,"插入结果失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public  CommonResult getPaymentById(@PathVariable("id") int id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("############查询的结果:",paymentById);
        if (paymentById != null){
            return new CommonResult(200,"查询成功,",paymentById);
        }else {
            return new CommonResult(444,"没有对应的记录，查询的id为："+id,null);
        }
    }

}