package com.zhi.springcloud.alibaba.controller;

import com.zhi.springcloud.alibaba.entities.CommonResult;
import com.zhi.springcloud.alibaba.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    //写一个静态代码块，模拟数据库
    public static HashMap<Integer, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1,new Payment(1,"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
        hashMap.put(2,new Payment(2,"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"));
        hashMap.put(3,new Payment(3,"CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"));
        hashMap.put(4,new Payment(4,"DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Integer id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> commonResult = new CommonResult(200, "模拟的 SQL 数据， ServerPort： " + serverPort, payment);
        return commonResult;
    }

}
