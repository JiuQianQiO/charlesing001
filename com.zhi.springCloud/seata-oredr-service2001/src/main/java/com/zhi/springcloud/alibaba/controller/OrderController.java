package com.zhi.springcloud.alibaba.controller;


import com.zhi.springcloud.alibaba.doamin.CommonResult;
import com.zhi.springcloud.alibaba.doamin.Order;
import com.zhi.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}