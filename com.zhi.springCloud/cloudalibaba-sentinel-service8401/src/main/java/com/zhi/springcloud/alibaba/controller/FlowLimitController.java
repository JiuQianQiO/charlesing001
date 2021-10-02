package com.zhi.springcloud.alibaba.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping(value = "/testA")
    public String testA(){
        return "-----------TestA";
    }

    @GetMapping(value = "/testB")
    public String testB(){
        return "-----------TestB";
    }

    /**
     * @SentinelResource 类似于 Hystrix 的 @HystrixCommand
     *          参数：value： value 的值是可以自定义的，只要保持唯一就行。我们一般和请求地址一样，
     *          用于设置规则的 资源名称，就是设置规则的时候第一个输入框要输入的资源名，必须是唯一的
     *          如果资源名称前面加了 ”/“, 表示使用 请求地址作为资源名称，如果不加 ”/“ 表示使用 @SentinelResource的值作为资源名称
     *          参数：blockHandler，当前请求违背了我们在 Sentinel客户端定义的规则后，调用参数值对应的方法来反馈给客户端（就是用来兜底，也就是fallback方法），
     *          如果不定义就是使用Sentinel默认的fallback：就是Blocked by Sentinel（flow limiting
     *          注意：这里的fallback（兜底方法） 是违背我们定义的规则才启用，
     *              如果是程序内部的异常或者错误，就该报错还是报错，是不会使用我们blockHandler定义的兜底方法
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public  String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                              @RequestParam(value = "p2",required = false) String p2){
        return "--------------testHotKey";

    }
    public String  deal_testHotKey(String p1, String p2, BlockException exception){
        return "--------------testHotKey——————————————";
        // Sentinel 默认的规则，返回值：Blocked by Sentinel（flow limiting）
    }

}
