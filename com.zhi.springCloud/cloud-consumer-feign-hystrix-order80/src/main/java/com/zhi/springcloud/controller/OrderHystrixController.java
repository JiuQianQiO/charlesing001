package com.zhi.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhi.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod")
//配置全局服务降级异常，反是加了 @HystrixCommand 注解的方法，没有指定服务降级的方法，就使用上面注解指定的服务降级的方
//                                            如果指定了服务降级的方法，就使用方法自己指定的服务降级的方法
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") int id){
        String info_ok = paymentHystrixService.paymentInfo_OK(id);
        return info_ok;
    }

    /**
     * @HystrixCommand 是用于服务熔断，其中参数：
     *      fallbackMethod ：当下面方法中发生错误或者异常，调用备用方法（服务降级的方法），备用方法的名称就是这个参数的值
     *      commandProperties 参数中  @HystrixProperty 设置这个方法的正常处理时间，在时间内处理完成，不算发生错误，不会降级
     *                              如果超过规定的处理时间，就调用备用方法
     *       如果程序中发生了错误，也会调用 备用方法（就是服务降级的方法）
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallback",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//    })
    @HystrixCommand // 设置当前方法开启服务降级处理，如果当前方法指定服务降级方法，就使用当前方法，没有指定
    //就使用类上面 @DefaultProperties 注解指定的服务降级方法
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String payment_TimeOut(@PathVariable("id") int id){
        int age = 10/0;
        String timeOut = paymentHystrixService.paymentInfo_TimeOut(id);
        return timeOut;
    }

    public String paymentInfo_TimeOutFallback(int id){
        return "线程池：    "+Thread.currentThread().getName() + "系统繁忙，稍后再试       paymentInfo_TimeOutFallback, id:     "+ id + "\t"+"🆗";
    }

    //下面是全局 fallback（服务降级） 方法，
    public String payment_global_fallbackMethod(){
        return "全局 服务降级处理当前问题，请稍等，（＞人＜；）";
    }
}
