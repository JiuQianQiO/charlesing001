package com.zhi.cloud.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     *  就是一个简单的方法，在这里不做数据库的调用
     * @param id
     * @return
     */
    public String paymentInfo_OK(int id){
        return "线程池：    "+Thread.currentThread().getName() + "       paymentInfo_ok, id:     "+ id + "\t"+"🆗";
    }

    /**
     * @HystrixCommand 是用于服务熔断，其中参数：
     *      fallbackMethod ：当下面方法中发生错误或者异常，调用备用方法，备用方法的名称就是这个参数的值
     *      commandProperties 参数中  @HystrixProperty 设置这个方法的正常处理时间，在时间内处理完成，不算发生错误，不会降级
     *                              如果超过规定的处理时间，就调用备用方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public  String paymentInfo_Timeout(int id){
        int timeOut = 30;
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池：    "+Thread.currentThread().getName() + "   id:     "+ id + "\t"+"🆗,此线程超时:♪(^∇^*)";

    }

    public String paymentInfo_TimeOutFallback(int id){
        return "线程池：    "+Thread.currentThread().getName() + "系统繁忙，稍后再试       paymentInfo_TimeOutFallback, id:     "+ id + "\t"+"🆗";
    }

    //============================================================================================
    //-----服务熔断实例--------

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 设置是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//设置请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 设置时间窗口期，
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    // 上面配置的含义是：
    //          第一个参数：当发生服务熔断的时候，调用 paymentCircuitBreaker_fallback 方法
    //          第二个参数： 1、配置开启服务熔断；
    //          第二、四个参数： 2、4、请求10次以上，失败率达到 60% 后开启调用服务熔断的方法，进行服务降级,
    //          第三个参数：在进行服务熔断以后，在时间窗口期内输入正确的地址也不能返回正确的页面，除非在时间窗口器访问的正确率上来了，就会回复访问


    public String paymentCircuitBreak(@PathVariable("id") int id){
        if (id < 0){
            throw  new RuntimeException("**************id 不能为负数，请稍后再试");
        }
        String serialNumber = String.valueOf(UUID.randomUUID());
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    //服务熔断后，fallback（服务降级）的方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") int id){
        return "您请求的 ID  为负数，请稍后再试！！ <( _ _ )> （＞人＜；）:    "+id;
    }

}
