package com.zhi.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhi.springcloud.alibaba.entities.CommonResult;
import com.zhi.springcloud.alibaba.entities.Payment;
import com.zhi.springcloud.alibaba.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    //fallback 兜底业务逻辑方法，只负责业务异常;; blockHandler 兜底规则，只负责规则异常；；；两个都配置，最后同时发生异常进入blockHandler
    public CommonResult<Payment> fallback(@PathVariable Integer id){

        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 5){
            throw  new IllegalArgumentException("IllegalArgumentException,非法参数异常");
        }else if (result.getData() == null){
            throw new NullPointerException("NullPointerException,没有对应的 ID 记录，空指针异常");
        }

        return  result;
    }

    //本方法是 fallback 的兜底方法，负责业务异常，不负责规则异常  异常的参数类型 Throwable
    public  CommonResult handlerFallback(@PathVariable Integer id, Throwable e){
        Payment payment = new Payment(id,"nullFallback");
        return new CommonResult<>(444,"兜底异常 handlerFallback，异常内容： "+e.getMessage(),payment);
    }

    // 本方法 是 blockHandler 的兜底异常 只负责规则异常，不负责业务异常 异常的类型 BlockException
    public CommonResult blockHandler(@PathVariable Integer id,BlockException blockException){
        Payment payment = new Payment(id,"null--blockHandler");
        return new CommonResult<>(555,"兜底异常 blockHandler，异常内容： "+blockException.getMessage(),payment);
    }

   // =============  OpenFeign  ==================
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Integer id){
        return paymentService.paymentSQL(id);
    }



}
