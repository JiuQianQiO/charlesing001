package com.zhi.cloud.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
