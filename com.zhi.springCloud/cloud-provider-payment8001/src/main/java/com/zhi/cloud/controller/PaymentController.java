package com.zhi.cloud.controller;




import com.zhi.cloud.entities.CommonResult;
import com.zhi.cloud.entities.Payment;
import com.zhi.cloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    //是否加 RequestBody 注解
    //是否加 RequestBody 注解,如过加了 注解，
    /**
     * 是否加 RequestBody 注解?
     * 如果加了 @RequestBody 注解，使用http://localhost:8002/payment/create?serial=8989000000 不能进行数据的插入
     *                           http://localhost/consumer/payment/create?serial=sdsds1212可以插入
     * 如果不加 @RequestBody 注解，使用http://localhost:8002/payment/create?serial=8989000000 可以插入数据，使用
     *                      http://localhost/consumer/payment/create?serial=sdsds1212可以插入
     *
     */
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("######插入结果成功："+result);
        if (result>0){
            return new CommonResult(200,"插入结果成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入结果失败,serverPort:"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public  CommonResult getPaymentById(@PathVariable("id") int id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("############查询的结果:",paymentById);
        if (paymentById != null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,paymentById);
        }else {
            return new CommonResult(444,"没有对应的记录，查询的id为："+id+";::ServerPort:"+serverPort,null);
        }
    }

    // 服务发现
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("@@@@@@@@@@@"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("$$$$$$$$$$$$$$$"+instance.getHost()+"::"+instance.getInstanceId()+"::"+instance.getPort()+"::"+instance.getUri());
        }
        return discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String  getPaymentLB(){
        return serverPort;
    }

}
