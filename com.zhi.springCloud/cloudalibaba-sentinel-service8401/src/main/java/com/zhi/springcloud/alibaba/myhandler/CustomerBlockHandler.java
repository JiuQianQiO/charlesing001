package com.zhi.springcloud.alibaba.myhandler;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhi.springcloud.alibaba.entities.CommonResult;
import com.zhi.springcloud.alibaba.entities.Payment;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return  new CommonResult(4444,"安装客户自定义，global handlerException---1",new Payment(2021,"customerBlockHandler"));
    }

    public static CommonResult handlerException2(BlockException exception){
        return  new CommonResult(4444,"安装客户自定义，global handlerException---2",new Payment(2021,"customerBlockHandler"));
    }
}
