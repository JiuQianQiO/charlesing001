package com.zhi.cloud.controller;




import com.zhi.cloud.entities.CommonResult;
import com.zhi.cloud.entities.Payment;
import com.zhi.cloud.lb.LoadBanancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBanancer loadBanancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){

        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public  CommonResult<Payment> getPayment(@PathVariable("id") int id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){

        //根据服务器集群的名称，获取当前服务器集群中所有服务器实例，就是Eureka中服务器集群的名称
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        // 过滤不可用的instance
        if (instances == null || instances.size() <= 0){
            return  null;
        }
        //调用自己的负载均衡算法，返回当前应该服务的服务器实例
        ServiceInstance serviceInstance = loadBanancer.instances(instances);
        // 获取当前服务器的uri
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);

    }
}
