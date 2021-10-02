package com.zhi.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhi.springcloud.alibaba.entities.CommonResult;
import com.zhi.springcloud.alibaba.entities.Payment;
import com.zhi.springcloud.alibaba.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping(value = "/byResources")
    @SentinelResource(value = "byResources",blockHandler = "handleException")
    public CommonResult byResources(){
        return new CommonResult(200,"安装资源名称限流OK",new Payment(2020,"Serial001"));
    }

    public CommonResult handleException(BlockException blockException){
        return new CommonResult(444,blockException.getClass().getCanonicalName()+"\t,该服务限制不可用，请稍后再试");
    }

    // CustomerBlockHandler
    @GetMapping(value = "/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
                        blockHandlerClass = CustomerBlockHandler.class,
                        blockHandler = "handlerException2")
    /**
     * 上面 @SentinelResource 注解配置的含义，value:定义唯一的资源名称。
                blockHandlerClass:定义当访问违背我们定义的规则，由 blockHandlerClass 对应的类来处理
                blockHandler：指定 当访问违背我们定义的规则 那个方法去兜底处理
      结合 blockHandlerClass 与  blockHandler 结合：就是当请求违背我们定义的规则，由指定类中的指定的方法去兜底处理
                        （指定的类：就是blockHandlerClass的值
                         指定的方法：就是 blockHandler 的值）
     这种方式实现了逻辑代码与处理异常的解耦和
     */
    public CommonResult  customerBlockHandler(){
        return new CommonResult(200,"",new Payment(2021,"customerBlockHandler"));
    }
}
