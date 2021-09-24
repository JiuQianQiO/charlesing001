package com.zhi.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBanancer {
    /**
     * 收集 Eureka 上面所有或者服务器的信息
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
